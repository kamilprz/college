/* SELF ASSESSMENT

 1. Did I use easy-to-understand meaningful variable names formatted properly (in lowerCamelCase)?

        Mark out of 5:  5 easy to understand variables

 2. Did I indent the code appropriately?

        Mark out of 5: 5 properly indented

 3. Did I write the createCipher function correctly (parameters, return type and function body) and invoke it correctly?

       Mark out of 20:  20 it creates and prints the cipher well

 4. Did I write the encrypt function correctly (parameters, return type and function body) and invoke it correctly?

       Mark out of 20:  20 it works

 5. Did I write the decrypt function correctly (parameters, return type and function body) and invoke it correctly?

       Mark out of 20:  20 it works

 6. Did I write the main function body correctly (repeatedly obtaining a string and encrypting it and then decrypting the encrypted version)?

       Mark out of 25:   25,  The program encrypts and decrypts as required, and loops until user enters 'exit'. 

 7. How well did I complete this self-assessment?

        Mark out of 5: 5 alright

 Total Mark out of 100 (Add all the previous marks): 100

 */ 
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Cipher {
	public static final int CHARACTERS_AVAILABLE=37;
	public static void main(String[] args) {
		try {
			boolean stop=false;
			char[] cipherArray = new char[CHARACTERS_AVAILABLE];
			String characters = "abcdefghijklmnopqrstuvwxyz0123456789 ";
			char[] charactersArray = characters.toCharArray();
			createCipher(charactersArray, cipherArray);
			while(stop!=true) {
				Scanner inputScanner = new Scanner(System.in);
				System.out.println("Would you like to encrypt or decrypt? ('exit' to quit)");
				if (inputScanner.hasNext("encrypt"))
				{
					System.out.println("Enter a string of characters you would like to encrypt.");
					inputScanner = new Scanner(System.in);
					String input = inputScanner.nextLine();
					String output = encrypt(input, charactersArray, cipherArray, characters);
					if(output=="") 
					{
						System.out.println("Please retry using the map from above.");
					}
					else if(output!="") {
						System.out.println("The encrypted version is, "+output+", and the decrypted version is, "+
											decrypt(output, charactersArray, cipherArray, characters)+".");
						
					}
				}
				else if(inputScanner.hasNext("decrypt")){
					System.out.println("Enter a string of cipher which you would like to decrypt.");
					inputScanner = new Scanner(System.in);
					String input = inputScanner.nextLine();
					String output = decrypt(input, charactersArray, cipherArray, characters);
					if(output=="") 
					{
						System.out.println("Please retry using the map from above.");
					}
					else if(output!="") 
					{
						System.out.println("The decrypted version is "+output+",and the encrypted version is, "+
											encrypt(output, charactersArray, cipherArray, characters)+".");
						
					}
				}
				else if(inputScanner.hasNext("exit"))
				{
					stop=true;
				}
			}
		}catch(InputMismatchException e) {
			System.out.println("Invalid input. Please retry.");
		}

	}


	public static void createCipher (char[]charactersArray, char[] cipherArray)  {
		for(int indexCounter=0; indexCounter<CHARACTERS_AVAILABLE; indexCounter++)
		{
			cipherArray[indexCounter] = charactersArray[indexCounter];
		}
		Random generator = new Random();
		for(int count=0; count<CHARACTERS_AVAILABLE;count++) 
		{
			int swap = generator.nextInt(CHARACTERS_AVAILABLE);
			char temp = cipherArray[count];
			cipherArray[count] = cipherArray[swap];
			cipherArray[swap] = temp;   
		}	
		System.out.println("This is the encryption map:\n"+Arrays.toString(charactersArray)+" -characters (decrypted) \n"+
							Arrays.toString(cipherArray)+" -cipher (encrypted)");
	}


	public static String encrypt(String input,char[]charactersArray,char[]cipherArray, String characters) {
		String lowercaseInput = input.toLowerCase();
		char[]inputArray=lowercaseInput.toCharArray();
		char[]outputArray = new char[inputArray.length];
		int index=0;
		for(int indexCounter=0; indexCounter<inputArray.length;indexCounter++) 
		{
			index = characters.indexOf(inputArray[indexCounter]);
			if (index < 0) {
				System.out.println("Invalid input on the character '" + inputArray[indexCounter]+"'.");
				String outputError="";
				return outputError;
			} else {
				outputArray[indexCounter]=cipherArray[index];
			}	
		}
		String outputEncrypted = new String(outputArray);
		return outputEncrypted;
	}

	public static String decrypt(String input,char[]charactersArray,char[]cipherArray,String characters) {
		String cipherCharacters = new String (cipherArray);
		String lowercaseInput = input.toLowerCase();
		char[]inputArray=lowercaseInput.toCharArray();
		char[]outputArray = new char[inputArray.length];
		int index=0;
		for(int indexCounter=0; indexCounter<inputArray.length;indexCounter++) 
		{
			index = cipherCharacters.indexOf(inputArray[indexCounter]);
			if (index < 0) {
				System.out.println("Invalid input on the character '" + inputArray[indexCounter]+"'.");
				String outputError="";
				return outputError;
			} else {
				outputArray[indexCounter]=charactersArray[index];
			}	
		}
		String outputEncrypted = new String(outputArray);
		return outputEncrypted;
	}
}



