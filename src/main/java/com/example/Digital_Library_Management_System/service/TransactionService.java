package com.example.Digital_Library_Management_System.service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.Digital_Library_Management_System.dto.InitiateTransactionRequest;
import com.example.Digital_Library_Management_System.model.Admin;
import com.example.Digital_Library_Management_System.model.Book;
import com.example.Digital_Library_Management_System.model.Student;
import com.example.Digital_Library_Management_System.model.Transaction;
import com.example.Digital_Library_Management_System.model.TransactionStatus;
import com.example.Digital_Library_Management_System.model.TransactionType;
import com.example.Digital_Library_Management_System.repository.TransactionDao;


@Service
public class TransactionService {


    @Autowired     StudentService studentService;

    @Autowired     BookService bookService;

    @Autowired     AdminService adminService;

    @Autowired     TransactionDao transactionDao;

    @Value("${student.allowed.max-books}")
    Integer maxBooksAllowed;

    @Value("${student.allowed.duration}")
    Integer allowedDuration;

    @Value("${student.fine-per-day}")
    Integer finePerDay;


    public String initiateTxn(InitiateTransactionRequest initiateTransactionRequest) throws Exception {


        return initiateTransactionRequest.getTransactionType() == TransactionType.ISSUE ? 
                issueBook(initiateTransactionRequest): 
                returnBook(initiateTransactionRequest);
       
    }

    /**
     * Issue a book (Issuance) -> {studentRollNumber, bookId, adminId}
     *      1. Validate the request => student, book and admin is valid or not
     *      2. Validate if book is available or not => If book is already issue on someone else's name
     *      3. Validate if the book can be issued to a person or not => We need to check if student has issue limit
     *          available in his account
     *      4. Entry in transaction table => pending status
     *      5. Assign book to a student => Update student column in book table
     *      6. Update the status
     * @throws Exception 
     **/

   

    private String issueBook(InitiateTransactionRequest initiateTransactionRequest) throws Exception {

        //1. Validating the request
        Student student = validateStudent(initiateTransactionRequest.getStudentRollNumber());
        if(student == null){
            throw new Exception("Invalid student roll number");
        }

        Admin admin = validateAdmin(initiateTransactionRequest.getAdminId());
        if(admin == null){
            throw new Exception("Invalid admin");
        }

        Book book = validateBook(initiateTransactionRequest.getBookId());
        if(book == null){
            throw new Exception("Invalid book");
        }

        // 2. Validating if the book is available or not
        if(book.getBook_student()!=null){
            throw new Exception("This book has already been assigned to " + book.getBook_student().getName());
        }

        // 3. Validating if the student can be issued the book/ if he has available limit to issue book
        if(student.getBookList().size() > maxBooksAllowed){
            throw new Exception("Issue limit reached for this student");
        }

        // 4. Build transaction and save it in txn table

        Transaction transaction = null;
        try{
            transaction = Transaction.builder()
                            .transactionId(UUID.randomUUID().toString())
                            .admin(admin)
                            .book(book)
                            .student(student)
                            .transactionStatus(TransactionStatus.PENDING)
                            .transactionType(TransactionType.ISSUE)
                            .build();

            // 5. Make entry in transaction table
            transactionDao.save(transaction);

            // 6. Assign book to the student
            book.setBook_student(student);
            bookService.createOrUpdateBook(book);

            // 7. Update the status of transaction
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        } catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
        }finally{
            transactionDao.save(transaction);
        }

        return transaction.getTransactionId();
    }
     /**
     * 1. Validate the request
     * 2. Get the corresponding issue transaction
     * 3. Entry in trxn table
     * 4. Due date of book, currentDate - issueDate > allowedDuration => fine calculation
     * 5. If there is fine => get the fine
     * 6. Deallocate the book from student's name => book table
     * 7. Update transaction status
     */

    private String returnBook(InitiateTransactionRequest initiateTransactionRequest) throws Exception {
       //1. Validating the request
       Student student = validateStudent(initiateTransactionRequest.getStudentRollNumber());
       if(student == null){
           throw new Exception("Invalid student roll number");
       }

       Admin admin = validateAdmin(initiateTransactionRequest.getAdminId());
       if(admin == null){
           throw new Exception("Invalid admin");
       }

       Book book = validateBook(initiateTransactionRequest.getBookId());
       if(book == null){
       }

       // 2. Get the corresponding issue txn
       Transaction issuanceTransaction = transactionDao.findBYStudentAndBookAndTransactionTypeOrderByIdAsc(book.getId(), student.getId(), TransactionType.ISSUE);

       //3. Update the return txn in txn table
       Transaction transaction = null;

       try{
        // 4. Due date to calcute fine
        Integer fine = calculateFine(issuanceTransaction.getCreatedOn());

        // 5. populate fine in txn table
        transaction = Transaction.builder()
                        .transactionId(UUID.randomUUID().toString())
                        .admin(admin)
                        .book(book)
                        .student(student)
                        .transactionType(TransactionType.RETURN)
                        .transactionStatus(TransactionStatus.PENDING)
                        .fine(fine)
                        .build();

        // Update the return txn in txn table
        transactionDao.save(transaction);

        // 6. Deallocate the student in book table
        book.setBook_student(null);
        bookService.createOrUpdateBook(book);

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
       }catch(Exception e){

        transaction.setTransactionStatus(TransactionStatus.FAILURE);

       }finally{

        transactionDao.save(transaction);

       }

       return transaction.getTransactionId();
    }



    private Book validateBook(Integer bookId) {
        try{
            return bookService.findBook("id", String.valueOf(bookId)).getFirst();
        }catch(Exception e){
            return null;
        }
      

    }


    private Admin validateAdmin(Integer adminId) {
        try{
            return adminService.findAdmin(adminId);
        }catch(Exception e){
            return null;
        }
    }


    private Student validateStudent(String studentRollNumber) {
        try{
            return studentService.findStudent("rollNumber", studentRollNumber);
        }catch(Exception e){
            return null;
        }
    }

       private Integer calculateFine(Date issuanceDateTime) {
        long issuanceTimeInMillis = issuanceDateTime.getTime();
        long currentTime = System.currentTimeMillis();

        long diff = currentTime-issuanceTimeInMillis;

        long daysPassed = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        if(daysPassed > allowedDuration) {
            return (int)((daysPassed - allowedDuration)*finePerDay);
        }
        return 0;
    }


    
}
