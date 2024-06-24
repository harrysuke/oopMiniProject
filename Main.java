package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static final List<Vep> veps = new ArrayList<>();
	private static final List<Pep> peps = new ArrayList<>();
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        int entityChoice;
        
        do {
        	System.out.println("Welcome to Safety and Security system");
        	System.out.println("1. Manage Vep");
        	System.out.println("2. Manage Pep");
        	System.out.println("3. Exit");
        	System.out.println("Enter your choice:");
        	entityChoice = scanner.nextInt();
        	scanner.nextLine();
        	
        	if(entityChoice == 1) {
        		manageVep(scanner);
        	}else if(entityChoice == 2) {
        		managePep(scanner);
        	}else if(entityChoice != 0) {
        		System.out.println("Invalid choice");
        	}
        }while(entityChoice != 0);

        
    }
    
    public static void manageVep(Scanner scanner) {
    	int choice = 0;
    	VEPOperations vepOperations = new VEPOperations();
    	
    	while (choice != 6){
            System.out.println("VEP Management\n");
            System.out.println("Please choose from the menu below:\n");
            System.out.println("1. Create new VEP (vehicle entry permit)");
            System.out.println("2. View all VEP records");
            System.out.println("3. Update existing VEP record");
            System.out.println("4. Search VEP by name");
            System.out.println("5. Delete VEP record");
            System.out.println("6. Export PEP records to text file");
            System.out.println("7. Back to Main Menu");
            System.out.println("Enter your choice:\n");

            try{
                choice = scanner.nextInt();
                switch (choice){
                    case 1:
                        System.out.println("Performing create new record operation...\n");
                        vepOperations.create();
                        break;
                    case 2:
                        System.out.println("Performing view record operation...");
                        vepOperations.read();
                        break;
                    case 3:
                        System.out.println("Performing udpate operation...");
                        vepOperations.update();
                        break;
                    case 4:
                        System.out.println("Performing search operation...");
                        vepOperations.search();
                        break;
                    case 5:
                        System.out.println("Performing delete operation...");
                        vepOperations.delete();
                        break;
                    case 6:
                    	System.out.println("Performing export operation...");
                    	vepOperations.exportToTextFile();
            			break;
                    case 7:
                        System.out.println("Exiting VEP Management...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number");
                }
            }catch (Exception e){
                System.out.println("Invalid input. Please enter a number");
                scanner.nextLine();
            }
        }
        //scanner.close();
    }
    
    public static void managePep(Scanner scanner) {
    	int choice = 0;
    	PEPOperations pepOperations = new PEPOperations(null, null, null, null, null, null, null, null, null, null, null, null, null);
    	
    	do {
    		System.out.println("PEP Management");
    		System.out.println("1. Register PEP");
    		System.out.println("2. View all PEP records");
    		System.out.println("3. Update PEP details");
    		System.out.println("4. Search PEP by name");
    		System.out.println("5. Delete PEP records");
    		System.out.println("6. Export PEP records to text file");
    		System.out.println("0. Back to Main Menu");
    		System.out.println("Enter your choice: ");
    		
    		choice = scanner.nextInt();
    		scanner.nextLine();
    		
    		switch(choice) {
    		case 1:
    			pepOperations.registerPep(scanner);
    			break;
    		case 2:
                System.out.println("Performing view record operation...");
                pepOperations.readPep(scanner);
                break;
    		case 3:
    			pepOperations.updatePep(scanner);
    			break;
    		case 4:
    			pepOperations.searchPep(scanner);
    			break;
    		case 5:
    			pepOperations.deletePep(scanner);
    			break;
    		case 6:
    			pepOperations.exportToTextFile();
    			break;
    		case 0:
    			System.out.println("Exiting PEP Management");
    			break;
    		default:
    			System.out.println("Invalid choice. Please enter a number");
    		}
    	}while(choice != 0);
    }
}