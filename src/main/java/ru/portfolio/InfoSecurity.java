package ru.portfolio;

import java.util.Scanner;

public class InfoSecurity {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter message, containing 4 symbols: ");
        String message = scanner.next();
        System.out.println("Enter start key, containing 16 symbols: ");
        String startKey = scanner.next();

        // 4 key chapters
        Long [] keyChapters = new Long[4];
        // key in hex representation
        StringBuilder startKeyHex = new StringBuilder();
        // message in Hex representation
        StringBuilder messageHex = new StringBuilder();
        for(int i = 0; i < startKey.length(); i++){
            // converting message from text to Hex
            if(i < 4){
                messageHex.append(Integer.toHexString(message.charAt(i)));
            }
            // converting key from text to Hex
            startKeyHex.append(Integer.toHexString(startKey.charAt(i)));
            //filling the key chapters with Long representation of 8 symbols in Hex,
            // so every keyChapter consists of Long number having 10 digits = 32 binaries
            if( i % 4 == 3) {
                keyChapters[i / 4] = Long.parseLong(startKeyHex.toString(),16);
                startKeyHex.setLength(0);
            }
        }

        System.out.println(messageHex + " = message in Hex");

        // making up an sBox from random numbers
        Long [] sBox = new Long[256];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 256; i++){
            //combining 4 hex numbers (of 2 symbols) into one 8 symbol hex
            for (int k = 0; k < 4; k++)
                // creating a random number, converting to char in ASCII,
                // then in Hex
                sb.append(Integer.toHexString((char) ((int) (Math.random()*255))));
            //converting 8 symbol Hex to Long
            sBox[i] = Long.parseLong(String.valueOf(sb),16);
            sb.setLength(0);
        }
        //getting the true key
        Long keyp = getKey(sBox, keyChapters);
        //converting key to Hex
        String finalKeyHex = Long.toHexString(keyp);
        System.out.println(finalKeyHex + " = the last key in Hex");

        //encrypting message
        StringBuilder encryptedMessage = new StringBuilder(),
                resHex = new StringBuilder();
        int temp;
        for(int i = 0; i < 7; i = i + 2){
            //partition Hex of key and Hex of message to 2 char values,
            // XOR integer representation of 2 symbol Hex
            temp = Integer.valueOf(finalKeyHex.substring(i, i + 2),16) ^
                    Integer.valueOf(messageHex.substring(i, i + 2),16);
            //getting Hex of encrypted message
            resHex.append(Integer.toHexString(temp));
            //getting encrypted message in ASCII
            encryptedMessage.append(Character.toChars(Integer.valueOf
                    (Integer.toHexString(temp),16)));
        }

        System.out.println(resHex + " = res in hex");
        System.out.println(encryptedMessage + " = encrypted message");

        //decryption
        StringBuilder decryptionHex = new StringBuilder(),
                decryptedMessage = new StringBuilder();
        for(int i = 0; i < 7; i = i + 2){
            //partition Hex of key and Hex of message to 2 char values,
            // XOR integer representation of 2 symbol Hex
            temp = Integer.valueOf(finalKeyHex.substring(i, i + 2),16) ^
                    Integer.valueOf(resHex.substring(i, i + 2),16);
            //getting Hex of decrypted message
            decryptionHex.append(Integer.toHexString(temp));
            //getting decrypted message in ASCII
            decryptedMessage.append(Character.toChars(Integer.valueOf
                    (Integer.toHexString(temp),16)));
        }
        System.out.println(decryptionHex + " = decrypted in Hex");
        System.out.println(decryptedMessage + " = decrypted message");
    }
    static Long getKey(Long [] sBox, Long [] keyChapters){

        long r3 = keyChapters[0] + keyChapters[3];
        r3 = ((r3 >> 8) ^ sBox[(int) (r3 & 255L)]);
        long r4 = r3 + keyChapters[1];
        r4 = (r4 >> 8) ^ sBox[(int) (r4 & 255L)];
        long r5 = r4 + keyChapters[2];
        r5 = (r5 >> 8) ^ sBox[(int) (r5 & 255L)];
        long r6 = r5 + keyChapters[3];
        r6 = (r6 >> 8) ^ sBox[(int) (r6 & 255L)];
        return r6;
    }
}
