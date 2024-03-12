package Problem_2.File;

import Problem_2.Drive.Drive;
import Problem_2.FileSystem.FileSystem;
import Problem_2.Folder.Folder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class File extends FileSystem {

    public File(String name,int size) {

        super("File");
        this.creationTime=LocalDateTime.now();
        this.name=name;
        this.directory+=":\\"+name;
        this.size=size;


    }
    @Override
    public void showDetails()
    {
        LocalDateTime currentTime= creationTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy h:mm a");
        String formattedTime = currentTime.format(formatter);

        System.out.println("Name: "+name);
        System.out.println("Type: "+type);
        System.out.println("Size: "+getSize()+" kB");
        System.out.println("Directory: "+directory);
        System.out.println("Component Count: "+getComponentCount());
        System.out.println("Creation Time: "+formattedTime);
    }
    @Override
    public int getSize()
    {
        return  this.size;
    }



}


