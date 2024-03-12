package Problem_2.Main;


import Problem_2.Drive.Drive;
import Problem_2.File.File;
import Problem_2.FileSystem.FileSystem;
import Problem_2.Folder.Folder;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        FileSystem root = new FileSystem("root");
        FileSystem current = root;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(current.getDirectory());
            String command = scanner.nextLine();
            switch (command.split(" ")[0].toLowerCase()) {
                case "mkdrive":
                    String name = command.split(" ")[1];
                    Drive d = new Drive(name);
                    current.addDrive(d);
                    break;
                case "cd":
                    name = command.split(" ")[1];
                    if (name.equalsIgnoreCase("~"))
                    {
                        current = root;
                    }
                    else
                    {
                        if(name.length()>=2)
                        {
                            String lastTwo = name.substring(name.length() - 2);

                            if(lastTwo.equals(":\\"))
                            {
                                name = name.substring(0, name.length() - 2);
                            }
                        }


                        FileSystem f = current.search(name);
                        if (f == null) {
                            System.out.println(name + " doesn't exist");
                        } else if (f.getType() .equals("File") ) {
                            System.out.println(name + " is a File");
                        }
                        else {
                            current = f;
                        }
                    }


                    break;
                case "mkdir":
                    name = command.split(" ")[1];

                    Folder folder = new Folder(name);
                    current.addFolder(folder);

                    break;
                case "ls":
                    name = command.split(" ")[1];

                    FileSystem fileSystem = current.search(name);
                    fileSystem.showDetails();
                    break;
                case "delete":
                    name = command.split(" ")[1];
                    if (name.equals("-r"))
                    {
                        name = command.split(" ")[2];
                        current.delete(name, true);

                    }
                    else
                    {
                        current.delete(name, false);
                    }

                    break;
                case "touch":
                    name=command.split(" ")[1];
                    if(command.split(" ").length!=3)
                    {
                        System.out.println("Incorrect Command");
                        continue;
                    }
                    String stringSize=command.split(" ")[2];
                    int size=Integer.parseInt(stringSize);
                    File f=new File(name,size);
                    current.addFile(f);

                    break;


                case "list":
                    current.getList();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Unknown command");
            }
        }


    }
}
