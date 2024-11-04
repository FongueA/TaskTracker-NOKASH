import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class TaskManagement {

    private static final String NEW_FILE_PATH = "tasks.json";
    private Calendar calendar = Calendar.getInstance();

    //Method to add a task
    public void add(String taskDescription){
        Task task = new Task(taskDescription);


        String lastLine;
        try{
            List <String> lines  = Files.readAllLines(Paths.get(NEW_FILE_PATH));
            if (!lines.isEmpty()){
                
                lastLine = lines.get(lines.size() - 1);
                JSONParser myParser = new JSONParser();
                try{ 
                    JSONObject theLastLine = (JSONObject) myParser.parse(lastLine);
                    Long id = (Long) theLastLine.get("ID");
                    System.out.println("Task added successfully  (ID "+ id +")");
                } catch(ParseException e){
                    e.printStackTrace();
                }
            } 
        }
        catch(Exception e){
                e.printStackTrace();
            }

        }


    //Method to update the task
    @SuppressWarnings("unchecked")
    public void update(int id, String newDescription){
         try{
             
            File myFile = new File(NEW_FILE_PATH);
        
            if (myFile.createNewFile()){
                System.out.println("    Create new tasks first please! ");
            }
            else{
                List<JSONObject> tasks = new ArrayList<>(); 
                JSONParser jsonParser = new JSONParser();

                try(BufferedReader readTask = new BufferedReader(new FileReader(NEW_FILE_PATH))){
                String line = readTask.readLine();
                int taskId = 0;
                while(line != null ){
                    
                    try{
                        JSONObject task = (JSONObject) jsonParser.parse(line);
                        if (id == ((Long) task.get("ID")).intValue()) {
                            task.put("Description", newDescription);
                            task.put("Updated_At",calendar.getTime().toString());
                            taskId = 1;
                        }
                        tasks.add(task); 
                    } catch(ParseException p) {
                        p.printStackTrace();
                    }
                    line = readTask.readLine();
                }

                if (taskId != 0){
                    System.out.println("The task has been updated successfully! ");
                }
                else {
                    System.out.println("The task with the ID '"+id+"' does not exists! These are the available tasks: ");
                    System.out.println();
                    this.list();
                }
                
            } catch(IOException e){

                e.printStackTrace();
            }
        

            try (FileWriter fileWriter = new FileWriter(NEW_FILE_PATH, false)) { 
                for (JSONObject task : tasks) {
                    fileWriter.write(task.toJSONString() + "\n"); 
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
                }
            } catch(IOException e){
                e.printStackTrace();
            } 
        
    }
    

    //Method to delete the task
    public void delete(int id){
        try{
             
            File myFile = new File(NEW_FILE_PATH);
        
            if (myFile.createNewFile()){
                System.out.println("    Create new tasks first please! ");
            }
            else{
                List<JSONObject> tasks = new ArrayList<>(); 
                JSONParser jsonParser = new JSONParser();

                try(BufferedReader readTask = new BufferedReader(new FileReader(NEW_FILE_PATH))){
                String line = readTask.readLine();
                int taskId = 0;
                while(line != null ){
                    
                    try{
                        JSONObject task = (JSONObject) jsonParser.parse(line);

                        if (id != ((Long) task.get("ID")).intValue()) {
                            tasks.add(task);
                        }
                        else{
                            taskId = 1;
                        }
                        
                    } catch(ParseException p) {
                        p.printStackTrace();
                    }
                    line = readTask.readLine();
                }

                if (taskId != 0){
                    System.out.println("The task has been deleted successfully! ");
                }
                else {
                    System.out.println("The task with the ID '"+id+"' does not exists! These are the available tasks: ");
                    System.out.println();
                    this.list();
                }
                
            } catch(IOException e){

                e.printStackTrace();
            }

            try (FileWriter fileWriter = new FileWriter(NEW_FILE_PATH, false)) { 
                for (JSONObject task : tasks) {
                    fileWriter.write(task.toJSONString() + "\n"); 
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        

    }


    //Method to mark the task as in-progress
    @SuppressWarnings("unchecked")
    public void mark_in_progress(int id){

        try{
             
            File myFile = new File(NEW_FILE_PATH);
        
            if (myFile.createNewFile()){
                System.out.println("    Please, create new task(s) first! ");
            }
            else{
                List<JSONObject> tasks = new ArrayList<>(); 
                JSONParser jsonParser = new JSONParser();

                try(BufferedReader readTask = new BufferedReader(new FileReader(NEW_FILE_PATH))){
                String line = readTask.readLine();
                int taskId = 0;
                while(line != null ){
                    
                    try{
                        JSONObject task = (JSONObject) jsonParser.parse(line);

                        if (id == ((Long) task.get("ID")).intValue()) {
                            task.put("Status", "in-progress");
                            task.put("Updated_At",calendar.getTime().toString());
                            taskId = 1;
                        }
                        tasks.add(task);
                        
                    } catch(ParseException p) {
                        p.printStackTrace();
                    }
                    line = readTask.readLine();
                }

                if (taskId != 0){
                    System.out.println("The task has been successfully marked as in-progress.");
                }
                else {
                    System.out.println("The task with the ID '"+id+"' does not exists! These are the available tasks: ");
                    System.out.println();
                    this.list();
                }
            
                } catch(IOException e){

                    e.printStackTrace();
                }

                try (FileWriter fileWriter = new FileWriter(NEW_FILE_PATH, false)) { 
                    for (JSONObject task : tasks) {
                        fileWriter.write(task.toJSONString() + "\n"); 
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        
    }


    //Method to mark the task as done
    @SuppressWarnings("unchecked")
    public void mark_done(int id){
        try{
             
            File myFile = new File(NEW_FILE_PATH);
        
            if (myFile.createNewFile()){
                System.out.println("    Please create tasks first!  ");
            }
            else{
                List<JSONObject> tasks = new ArrayList<>(); 
                JSONParser jsonParser = new JSONParser();

                try(BufferedReader readTask = new BufferedReader(new FileReader(NEW_FILE_PATH))){
                String line = readTask.readLine();
                int taskId = 0;
                while(line != null ){
                    
                    try{
                        JSONObject task = (JSONObject) jsonParser.parse(line);

                        if (id == ((Long) task.get("ID")).intValue()) {
                            task.put("Status", "done");
                            task.put("Updated_At",calendar.getTime().toString());
                            taskId = 1;
                        }
                        tasks.add(task);
                        
                    } catch(ParseException p) {
                        p.printStackTrace();
                    }
                    line = readTask.readLine();
                    if (taskId != 0){
                        System.out.println("The task has been successfully marked as done.");
                    }
                    else {
                        System.out.println("The task with the ID '"+id+"' does not exists! These are the available tasks: ");
                        System.out.println();
                        this.list();
                    }
                    
                }
                
                } catch(IOException e){

                    e.printStackTrace();
                }

                try (FileWriter fileWriter = new FileWriter(NEW_FILE_PATH, false)) { 
                    for (JSONObject task : tasks) {
                        fileWriter.write(task.toJSONString() + "\n"); 
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                    }
        } catch(IOException e){
            e.printStackTrace();
        }
        
    }


    //Method to list all the tasks
    public void list() {
        
       try{
             
            File myFile = new File(NEW_FILE_PATH);
        
            if (myFile.createNewFile()){
                System.out.println("    Please wait until tasks are created! ");
            }
            else
            {
                try{
                    List <String> lines  = Files.readAllLines(Paths.get(NEW_FILE_PATH));
            
                    if (!lines.isEmpty()){
                        JSONParser jsonParser = new JSONParser();
                        try(BufferedReader readTask = new BufferedReader(new FileReader(NEW_FILE_PATH))){
                        String line = readTask.readLine();
                        System.out.println("The list of tasks is as follow: \n");
                
                        while(line != null ){

                            try{
                                JSONObject task = (JSONObject) jsonParser.parse(line);
                                System.out.println("ID:  "+ task.get("ID"));
                                System.out.println("Description:  "+ task.get("Description"));
                                System.out.println("Status:  "+ task.get("Status"));
                                System.out.println("Created at:  "+ task.get("Created_At"));
                                System.out.println("Last updated at:  "+ task.get("Updated_At"));
                                System.out.println("------------------------------------------------------------");
                            } catch(ParseException p) {
                                p.printStackTrace();
                            }
                            line = readTask.readLine();
                        }
                
                        } catch(IOException e){

                            e.printStackTrace();
                        }

                    } else {
                        System.out.println("    The list is empty! ");

                    }
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                
            }
            }   catch(IOException e){
                e.printStackTrace();
            }
                
    }

    //Method to list the tasks that are done
    public void list_done(){
        final String STAT1 = "done";

        try{
             
            File myFile = new File(NEW_FILE_PATH);
        
            if (myFile.createNewFile()){
                System.out.println("    Please wait until tasks are created! ");
            }
            else{

                JSONParser jsonParser = new JSONParser();

                try(BufferedReader readTask = new BufferedReader(new FileReader(NEW_FILE_PATH))){
                String line = readTask.readLine();
                System.out.println("List of tasks done: \n");
                int nb_done_tasks = 0;
                while(line != null ){
                try{
                    JSONObject task = (JSONObject) jsonParser.parse(line);
                    String status = (String) task.get("Status");
                    if (STAT1.equals(status)){
                        System.out.println("ID:  "+ task.get("ID"));
                        System.out.println("Description:  "+ task.get("Description"));
                        System.out.println("Status:  "+ task.get("Status"));
                        System.out.println("Created at:  "+ task.get("Created_At"));
                        System.out.println("Last updated at:  "+ task.get("Updated_At"));
                        System.out.println("------------------------------------------------------------");
                        nb_done_tasks += 1;
                    }
                    
                } catch(ParseException p) {
                    p.printStackTrace();
                }
                line = readTask.readLine();
                }
                if (nb_done_tasks == 0){
                    System.out.println("    There is not yet tasks completed already! ");
                }
        
                } catch(IOException e){

                    e.printStackTrace();
                }

            }
        } catch(IOException e){
            e.printStackTrace();
        }

    }


    //Method to list the tasks that are todo 
    public void list_todo() {

        final String STAT2 = "todo";

        try{
             
            File myFile = new File(NEW_FILE_PATH);
        
            if (myFile.createNewFile()){
                System.out.println("    Please wait until tasks are created! ");
            }
            else{
                JSONParser jsonParser = new JSONParser();

                try(BufferedReader readTask = new BufferedReader(new FileReader(NEW_FILE_PATH))){
                String line = readTask.readLine();
                System.out.println("List of tasks to do: \n");
                int todoTasks = 0;
                while(line != null ){  
                try{
                    JSONObject task = (JSONObject) jsonParser.parse(line);
                    String status = (String) task.get("Status");
                    if (STAT2.equals(status)){
                        System.out.println("ID:  "+ task.get("ID"));
                        System.out.println("Description:  "+ task.get("Description"));
                        System.out.println("Status:  "+ task.get("Status"));
                        System.out.println("Created at:  "+ task.get("Created_At"));
                        System.out.println("Last updated at:  "+ task.get("Updated_At"));
                        System.out.println("------------------------------------------------------------");
                        todoTasks +=1;
                    }
                    
                } catch(ParseException p) {
                    p.printStackTrace();
                }
                line = readTask.readLine();
                }
                if (todoTasks == 0){
                    System.out.println("    There is no more tasks to do! ");
                }
                
                } catch(IOException e){

                    e.printStackTrace();
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
     
    }


    //Method to list the tasks in progress
    public void list_in_progress(){
        final String STAT3 = "in-progress";
        try{
             
            File myFile = new File(NEW_FILE_PATH);
        
            if (myFile.createNewFile()){
                System.out.println("    Please wait until tasks are created! ");
            }
            else{
                JSONParser jsonParser = new JSONParser();

                try(BufferedReader readTask = new BufferedReader(new FileReader(NEW_FILE_PATH))){
                String line = readTask.readLine();
                System.out.println("List of tasks in progress: \n");
                int inProgressTasks = 0;
                while(line != null ){

                try{
                    JSONObject task = (JSONObject) jsonParser.parse(line);
                    String status = (String) task.get("Status");
                    if (STAT3.equals(status)){
                        System.out.println("ID:  "+ task.get("ID"));
                        System.out.println("Description:  "+ task.get("Description"));
                        System.out.println("Status:  "+ task.get("Status"));
                        System.out.println("Created at:  "+ task.get("Created_At"));
                        System.out.println("Last updated at:  "+ task.get("Updated_At"));
                        System.out.println("------------------------------------------------------------");
                        inProgressTasks +=1;

                    }
                    
                }catch(ParseException p) {
                    p.printStackTrace();
                }
                line = readTask.readLine();
                }
                if (inProgressTasks == 0){

                    System.out.println("    There is no task in progress! ");
                }
                
                } catch(IOException e){

                    e.printStackTrace();
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }    
    
    }



}
