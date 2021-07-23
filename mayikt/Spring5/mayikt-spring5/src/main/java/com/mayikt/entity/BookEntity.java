package com.mayikt.entity;

/**
 * @author 周宇
 * @create 2021-07-23 16:57
 */
public class BookEntity {

    private String bookName;
    private Double bookPrice;

    public BookEntity() {
    }

    public BookEntity(String bookName, Double bookPrice) {
        this.bookName = bookName;
        this.bookPrice = bookPrice;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        System.out.println("bookName:" + bookName + ",底层基于java的反射机制，调用set方法实现赋值");
        this.bookName = bookName;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "bookName='" + bookName + '\'' +
                ", bookPrice=" + bookPrice +
                '}';
    }

    public static void main(String[] args) {
        //1.创建对象和set方法注入属性
        //BookEntity bookEntity = new BookEntity();
        //bookEntity.setBookName("Code代码大全");
        //bookEntity.setBookPrice(99.00);

        //2.有参构造函数注入属性
        BookEntity bookEntity = new BookEntity("Code代码大全", 99.00);
        System.out.println(bookEntity.toString());
    }
}
