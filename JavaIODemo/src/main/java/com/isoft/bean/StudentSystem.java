package com.isoft.bean;

import org.junit.Test;

import javax.swing.*;
import java.io.*;
import java.nio.Buffer;
import java.util.*;

/**
 * 功能描述:
 *
 * @author: 许宏超 1630090038
 * @Date: 2019/1/12 10:58
 */
public class StudentSystem {
    List <Map <String, String>> usetlist;

    Scanner scanner;
    private BufferedWriter bw;
    private FileWriter fw;
    private RandomAccessFile raf;
    private String filename;
    private int i;

    public StudentSystem() {
        try {
            usetlist = new ArrayList <Map <String, String>>();
            filename = "StudentInfo.txt";
            fw = new FileWriter( filename, true );
            bw = new BufferedWriter( fw );
            raf = new RandomAccessFile( filename, "rw" );
            scanner = new Scanner( System.in );
            Map map = new Hashtable();
            map.put( "zhangsan", "111111" );
            usetlist.add( map );
            Map map1 = new Hashtable();
            map1.put( "lisi", "222222" );
            usetlist.add( map1 );
            Map map2 = new Hashtable();
            map2.put( "wangwu", "333333" );
            usetlist.add( map2 );

        } catch (Exception e) {
            e.getStackTrace();
        }


    }

    ///login
    public void Login() throws Exception {
        System.out.println( "学生信息管理系统" );
        System.out.print( "请输入用户名 ：" );
        String username = scanner.next();
        System.out.print( "请输入密码" );
        String password = scanner.next();
        Map map = new Hashtable();
        map.put( "user", "pawd" );
        if (usetlist.contains( map )) {
            System.out.println( "欢迎" );
            System.out.println( username );
            System.out.println( "登录成功" );
            startSystem();
        }
        System.out.println( "登录失败" );
    }

    @Test
    public void startSystem() throws Exception {

        try {
            boolean flag = true;
            while (flag) {
                Menu.startMenu();
                System.out.println( "请输入用户选择" );
                final String op = scanner.next();
                switch (op) {
                    case "1":
                        addStudentInfo();
                        break;
                    case "2":
                        findStudentInfo();
                        break;
                    case "3":
                        findAllStudentInfo();
                        break;
                    case "4":
                        delateStudentinfo();
                        break;
                    case "5":
                        exitSystem();
                        break;
                    default:
                        System.out.println( "输入错误" );
                }
            }

            raf.close();
            System.out.println( "是否继续使用 1 是 0 否" );
            i = scanner.nextInt();
            if (i == 0) {
                flag = false;
            }
            System.out.println( "欢迎下次再来" );
            bw.close();
            fw.close();
            raf.close();

            System.out.println( "欢迎下次再来" );
        } catch (Exception e) {
            e.getStackTrace();
        }

    }


    private void exitSystem() {
        try {
            raf.seek( 0 );
            System.out.println( "是否退出本系统?(1:是，0：否)" );
            int Temp = scanner.nextInt();
            if (Temp == 1) {
                System.exit( 0 );
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void delateStudentinfo() throws Exception {
        // 将指针指到万就能的首字母
        raf.seek( 0 );
        System.out.println( "请输入要删除的学号" );
        String sid = scanner.next();
        String str = null;
        ArrayList <String> stulit = new ArrayList <String>();
        while ((str = raf.readLine()) != null) {
            // 判断不等， 时候
            if (!str.split( "," )[0].equalsIgnoreCase( sid )) {
                stulit.add( str );
            }
        }
        // 重置下文件
        fw = new FileWriter( filename );
        // 迭代器
        Iterator <String> iterator = stulit.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            bw.write( s );
            bw.newLine();
            bw.flush();
        }
    }

    private void addStudentInfo() throws Exception {

        boolean flag = true;
        while (flag) {
            System.out.println( "请输入学号" );

            String sid = scanner.next();
            System.out.println( "请输入姓名" );
            String name = scanner.next();
            System.out.println( "请输入性别" );
            String sex = scanner.next();
            System.out.println( "请输入成绩" );
            String score = scanner.next();
            System.out.println( sid + name + sex + score );
            String filename = "StudentInfo.txt";
            // 直接就创建文件了
            // true 创建变成追加

            bw.write( sid + "," + name + "," + score + "," );
            // 换行符
            bw.newLine();
            // 打开一次必须关掉
            bw.flush();
            // 刷新缓冲区
            System.out.println( "是否继续输入 0是退出 1 是再输入" );
            i = scanner.nextInt();
            if (i == 0) {
                flag = false;
            }

        }
    }

    // 查找
    private void findStudentInfo() throws Exception {
        raf.seek( 0 );
        System.out.println( "请输入学号" );
        String sid = scanner.next();
        String str;
        System.out.println( "查找学生信息" );
        System.out.println( "-----------------------" );
        int Temp = 0;
        while ((str = raf.readLine()) != null) {
            String[] strarr = str.split( "," );
            // 字符串匹配是 用equals
            if (strarr[0].equalsIgnoreCase( sid )) {
                Temp += 1;
                System.out.println( Temp + ",学号: " +
                        changeUtf8.getUtf8( strarr[0] ) + "姓名: " + changeUtf8.getUtf8( strarr[1] ) +
                        "分数" + changeUtf8.getUtf8( strarr[2] )
                );
            }
            Thread.sleep( 1000 );
        }
        if (Temp == 0) {
            System.out.println( "没有找到学生信息" );
            System.out.println( "--------------------" );
        }
    }

    private void findAllStudentInfo() throws Exception {


        try {
            raf.seek( 0 );
            int Temp = 0;
            System.out.println( "查找全部信息:" );
            String rowStr;
            if ((rowStr = raf.readLine()) != null) {
                Temp++;
                String[] strarr = rowStr.split( "," );
                System.out.println( Temp + ",学号: " +
                        changeUtf8.getUtf8( strarr[0] ) + "姓名: " + changeUtf8.getUtf8( strarr[1] ) +
                        "分数" + changeUtf8.getUtf8( strarr[2] )
                );
                Thread.sleep( 1000 );
            }
            if (Temp == 0) {
                System.out.println( "没有任何信息" );
            }
        }catch (Exception e){
            e.getStackTrace();

        }
    }

}

