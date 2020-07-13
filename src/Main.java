import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static int F,PC,AC = 0; //Defining Flag, ProgramCounter and Accumulator
    static int[] Memory = new int[1024]; //Represents 256bit memory, As 256 is out of range fore byte I used int
    static ArrayList<String> programCommands = new ArrayList<>(); //Creating array list to hold each command individually


    public static void main(String[] args) throws FileNotFoundException {
        File textFile = new File(args[0]);
        Scanner dataScan = new Scanner(textFile); //Creating scanner in order to take program.txt

        while (dataScan.hasNextLine()) { //Checking program.txt line by line
            programCommands.add(dataScan.nextLine()); //Any found line is added to programCommands
        }

        if (programCommands.get(0).contains("START")) { //Checking if the first statement is START to start emulator
            for (int i = 1; i < programCommands.size(); i++) {
                PC=i+1;
                String[] commandsInduvidial = programCommands.get(i).split(" "); //Takes commands to string individually
                byte selector = 0; //Variable for selecting each command
                if (commandsInduvidial[selector+1].equals("HALT")) break; //If the HALT command comes system stops working
                selector++;
                switch (commandsInduvidial[selector]){ //Selecting third element to find correct method
                    case "LOAD": LOAD(Integer.valueOf(commandsInduvidial[selector+1])); break;
                    case "LOADM": LOADM(Integer.valueOf(commandsInduvidial[selector+1])); break;
                    case "STORE": STORE(Integer.valueOf(commandsInduvidial[selector+1])); break;
                    case "CMPM": CMPM(Integer.valueOf(commandsInduvidial[selector+1])); break;
                    case "CJMP": CJMP(Integer.valueOf(commandsInduvidial[selector+1])); i=PC-1; break;
                    case "JMP": JMP(Integer.valueOf(commandsInduvidial[selector+1])); i=PC-1; break;
                    case "ADD": ADD(Integer.valueOf(commandsInduvidial[selector+1])); break;
                    case "ADDM": ADDM(Integer.valueOf(commandsInduvidial[selector+1])); break;
                    case "SUB": SUB(Integer.valueOf(commandsInduvidial[selector+1])); break;
                    case "SUBM": SUBM(Integer.valueOf(commandsInduvidial[selector+1])); break;
                    case "MUL":  MUL(Integer.valueOf(commandsInduvidial[selector+1])); break;
                    case "MULM": MULM(Integer.valueOf(commandsInduvidial[selector+1])); break;
                    case "DISP": DISP(); break;
                }
            }
        }
    }

    public static void LOAD(int x){ //Loading immediate value
        AC = x; //Setting accumulator value to x
    }

    public static void LOADM(int x){ //Loading memory value
        AC = Memory[x]; //Setting accumulator value to x location element of Memory
    }

    public static void STORE(int x) { //Storing a value
        Memory[x] = AC; //Setting Memories x location to accumulator value
    }

    public static void CMPM(int x){ //Comparing whether accumulator or memory value is bigger
        if (AC>Memory[x]) F = 1;
        else if (AC<Memory[x]) F = -1;
        else F = 0;
    }

    public static void JMP(int x){ //Unconditional Jump
        PC = x; //Directly setting ProgramCounter value to x
    }

    public static void CJMP(int x){ //Conditional Jump
        if (F==1) PC = x; //First checking if flag is 1 than setting ProgramCounter value to x
    }

    public static void ADD(int x){ //Immediate addition
        AC = AC + x; //Directly adding x to accumulator value
    }

    public static void ADDM(int x){ //Memory addition
        AC= AC + Memory[x]; //Directly adding a value from memories x location to accumulator value
    }

    public static void SUB(int x){ //Immediate subtraction
        AC= AC - x; //Directly subtracting x from accumulator value
    }

    public static void SUBM(int x){ //Memory subtraction
        AC= AC - Memory[x]; //Directly subtracting memories x location element from accumulator
    }

    public static void MUL(int x){ //Immediate multiplication
        AC= AC * x; //Directly multiplying accumulator value with x
    }

    public static void MULM(int x){ //Memory multiplication
        AC= AC * Memory[x]; //Multiplying accumulator value with memories x location value
    }

    public static void DISP(){ //Displaying accumulator value on screen
        System.out.println(AC);
    }

}

