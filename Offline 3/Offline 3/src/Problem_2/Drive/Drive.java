package Problem_2.Drive;

import Problem_2.File.File;
import Problem_2.FileSystem.FileSystem;
import Problem_2.Folder.Folder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Drive extends FileSystem {
    private List<File> fileList;
    private List<Folder> folderList;


    public Drive(String name)
    {
        super("Drive");
        this.creationTime= LocalDateTime.now();
        fileList=new ArrayList<>();
        folderList=new ArrayList<>();
        this.name=name;
        this.directory+=":\\"+name;
    }

    @Override
    public void delete(String name,boolean isRecursive) {

        if(!isRecursive)
        {
            deleteFile(name);
            deleteFolder(name);
        }
        else
        {
            recursiveDeleteFile(name);
            recursiveDeleteFolder(name);

        }

    }

    public void recursiveDeleteFile(String name)
    {
        for(int i=0;i<fileList.size();i++)
        {
            if(fileList.get(i).getName().equals(name))
            {
                System.out.println("Warning: " + fileList.get(i).getName() + " deleted");
                fileList.remove(i);
                break;
            }
        }
    }

    public void recursiveDeleteFolder(String name)
    {
        for(int i=0;i<folderList.size();i++)
        {

            if(folderList.get(i).getName().equals(name))
            {
                folderList.get(i).recursiveDelete();


                System.out.println(folderList.get(i).getName() + " deleted");
                folderList.remove(i);
                break;

            }
        }
    }


    public void deleteFile(String name)
    {
        for(int i=0;i<fileList.size();i++)
        {
            if(fileList.get(i).getName().equals(name))
            {
                fileList.remove(i);
                break;
            }
        }
    }

    public void deleteFolder(String name)
    {
        Iterator<Folder> iterator = folderList.iterator();
        while (iterator.hasNext()) {
            Folder folder = iterator.next();
            if(folder.getComponentCount()==0 && folder.getName().equals(name))
            {
                iterator.remove();
                break;
            }
        }

    }

    public FileSystem search(String name)
    {
        for(int i=0;i<folderList.size();i++)
        {
            if(name.equals(folderList.get(i).getName()))
            {
                return folderList.get(i);
            }
        }

        for(int i=0;i<fileList.size();i++)
        {
            if(name.equals(fileList.get(i).getName()))
            {
                return fileList.get(i);
            }
        }
        return null;
    }

    @Override
    public void addFile(File f) {
            fileList.add(f);
            f.setDirectory(this.directory+":\\"+f.getName());

    }

    @Override
    public void addFolder(Folder f) {
            folderList.add(f);
            f.setDirectory(this.directory+":\\"+f.getName());

    }

    @Override
    public int getSize()
    {
        int size=0;
        for(int i=0;i<fileList.size();i++)
        {
            size+=fileList.get(i).getSize();
        }
        for(int i=0;i<folderList.size();i++)
        {
            size+=folderList.get(i).getSize();
        }
        return size;
    }
    @Override
    public void getList()
    {
        for(int i=0;i<folderList.size();i++)
        {
            Folder f=folderList.get(i);
            System.out.println(f.getName()+"    "+f.getSize()+" kB  "+f.getCreationTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        }
        for(int i=0;i<fileList.size();i++)
        {
            File f=fileList.get(i);
            System.out.println(f.getName()+"    "+f.getSize()+" kB  "+f.getCreationTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        }
    }
    @Override
    public int getComponentCount()
    {
        int count=0;
        for(int i=0;i<fileList.size();i++) count++;
        for(int i=0;i<folderList.size();i++) count+=folderList.get(i).getComponentCount();
        return count;
    }
}
