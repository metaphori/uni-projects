/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ul.roby.ea.ejb;

import ul.roby.ea.entities.Book;

/**
 *
 * @author Roberto Casadei, 11153555
 */
   
    public class CartEntry {
        private Book book;
        private int quantity;
        
        public CartEntry(Book book, int quantity){
            this.book = book;
            this.quantity = quantity;
        }
        
        public Book getBook(){ return book; }
        public int getQuantity(){ return quantity; }
        
        public void setQuantity(int quantity){ this.quantity = quantity; }
        public void setBook(Book book){ this.book = book; }
    }
