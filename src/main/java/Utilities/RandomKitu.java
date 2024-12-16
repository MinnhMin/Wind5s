/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import java.util.Random;

/**
 *
 * @author Admin
 */
public class RandomKitu {
    public static void main(String[] args) {
        // In ra chuỗi ngẫu nhiên được tạo từ phương thức randomStr()
        System.out.println(randomStr());
    }
    
    // tạo chuỗi ngẫu nhiên.
    public static String randomStr(){
        // Sử dụng StringBuilder để tạo chuỗi
        StringBuilder sb = new StringBuilder();
        
        // Tạo đối tượng Random để tạo số ngẫu nhiên
        Random rd = new Random();
        
        // Thêm một số nguyên ngẫu nhiên vào chuỗi StringBuilder
        sb.append(rd.nextInt());
        
        // Trả về chuỗi ngẫu nhiên dưới dạng String
        return sb.toString();
    }
}
