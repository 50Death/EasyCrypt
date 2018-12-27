/***
 * @author LYC
 * @version ultimate
 * @language java
 */
//基于秘钥的单表代换密码
package vamos.crypt;

import java.util.Scanner;

public class Crypt1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Usage:\nInput the front number to select option\n1. encipher\n2. decipther\n0. EXIT\n");
        try {
            while (true) {
                int contents = input.nextInt();//1 means enciphering, 2 means deciphering
                input.nextLine();

                if (contents == 1) {
                    String clear = new String();
                    String key = new String();
                    System.out.print("Input clear text:  ");
                    clear = input.nextLine();//明文
                    System.out.print("Input key:  ");
                    key = input.next();//秘钥
                    input.nextLine();
                    clear = clear.toUpperCase();
                    key = key.toUpperCase();
                    for (int i = 0; i < clear.length(); i++) {
                        if (clear.charAt(i) < 'A' && clear.charAt(i) > 'Z' && clear.charAt(i) != ' ') {
                            System.out.print("You can only input letters and space");
                            continue;
                        }
                    }
                    String result = new String();
                    result = enCipher(clear, key);
                    System.out.print("CipherText: " + result + '\n');
                } else if (contents == 2) {
                    String ciphertext2 = new String();
                    String clear2 = new String();
                    String key2 = new String();
                    System.out.print("Input ciphertext: ");
                    ciphertext2 = input.nextLine();
                    System.out.print("Input key: ");
                    key2 = input.next();
                    input.nextLine();
                    ciphertext2 = ciphertext2.toUpperCase();
                    key2 = key2.toUpperCase();
                    for (int i = 0; i < ciphertext2.length(); i++) {
                        if (ciphertext2.charAt(i) < 'A' && ciphertext2.charAt(i) > 'Z' && ciphertext2.charAt(i) != ' ') {
                            System.out.print("You can only input letters and space");
                            continue;
                        }
                    }
                    clear2 = deCipher(ciphertext2, key2);
                    System.out.print("ClearText: " + clear2 + '\n');
                } else if (contents == 0)
                    break;
                else {
                    System.err.print("[WARNING]Incorrect input!\n");
                    System.out.print("Usage:\nInput the front number to select option\n1. encipher\n2. decipther\n0. EXIT\n");
                }
            }
        } catch (ArrayIndexOutOfBoundsException error) {
            System.err.print(error + "\n");
        }
    }

    public static String enCipher(String clear, String key) {
        int clength = clear.length();
        int klength = key.length();
        if (klength <= 26) {
            String plane = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            char[] temp2 = key.toCharArray();
            for (int i = 0; i < klength; i++) {
                plane = plane.replaceAll(String.valueOf(temp2[i]), "");
            }
            key = key + plane;
        }
        tableGenerator(key);
        char[] cipher1 = clear.toCharArray();
        char[] cipher2 = key.toCharArray();
        char[] charesult = new char[clength];
        for (int i = 0; i < clength; i++) {
            if (cipher1[i] == ' ') continue;
            int asciitemp = (int) cipher1[i] - (int) 'A';
            charesult[i] = cipher2[asciitemp];
        }
        String result = new String(charesult);
        return result;
    }

    public static String deCipher(String crypt, String key) {
        int clength = crypt.length();
        int klength = key.length();
        if (klength <= 26) {
            String plane = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            char[] temp2 = key.toCharArray();
            for (int i = 0; i < klength; i++) {
                plane = plane.replaceAll(String.valueOf(temp2[i]), "");
            }
            key = key + plane;
        }
        tableGenerator(key);
        char[] cipher1 = crypt.toCharArray();
        char[] cipher2 = key.toCharArray();
        char[] charesult = new char[clength];
        for (int i = 0; i < clength; i++) {
            if (cipher1[i] == ' ') continue;
            for (int j = 0; j < 26; j++) {
                if (cipher1[i] == cipher2[j]) {
                    charesult[i] = (char) (j + 'A');
                }
            }
        }
        String result = new String(charesult);
        return result;
    }

    public static void tableGenerator(String key) {
        String line = new String("-----------------------------------------------------\n");
        System.out.print(line);
        char ch = 'A';
        for (int i = 0; i < 26; i++) {
            System.out.print("|" + (char) (ch + i));
        }
        System.out.print("|" + '\n');
        char[] temp = key.toCharArray();
        for (int i = 0; i < 26; i++) {
            System.out.print("|" + temp[i]);
        }
        System.out.print("|" + '\n' + line);
    }
}
