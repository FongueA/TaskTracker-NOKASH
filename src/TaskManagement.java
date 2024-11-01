import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

        System.out.println("Task added successfully  (ID "+task.getId()+")");
    }


    //Method to update the task
    @SuppressWarnings("unchecked")
    public void update(int id, String newDescription){
        
        List<JSONObject> tasks = new ArrayList<>(); 
        JSONParser jsonParser = new JSONParser();

        try(BufferedReader readTask = new BufferedReader(new FileReader(NEW_FILE_PATH))){
            String line = readTask.readLine();
            while(line != null ){
                
                try{
                    JSONObject task = (JSONObject) jsonParser.parse(line);
                    if (id == ((Long) task.get("ID")).intValue()) {
                        task.put("Description", newDescription);
                        task.put("Updated_At",calendar.getTime().toString());
                    }
                    tasks.add(task); 
                } catch(ParseException p) {
                    p.printStackTrace();
                }
                line = readTask.readLine();
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
        
        System.out.println("The task has been updated successfully.");
    }
    

    //Method to delete the task
    public void delete(int id){
        List<JSONObject> tasks = new ArrayList<>(); 
        JSONParser jsonParser = new JSONParser();

        try(BufferedReader readTask = new BufferedReader(new FileReader(NEW_FILE_PATH))){
            String line = readTask.readLine();
            while(line != null ){
                
                try{
                    JSONObject task = (JSONObject) jsonParser.parse(line);

                    if (id != ((Long) task.get("ID")).intValue()) {
                        tasks.add(task);
                    }
                     
                } catch(ParseException p) {
                    p.printStackTrace();
                }
                line = readTask.readLine();
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
        
        System.out.println("The task has been deleted successfully.");

    }


    //Method to mark the task as in-progress
    @SuppressWarnings("unchecked")
    public void mark_in_progress(int id){
        List<JSONObject> tasks = new ArrayList<>(); 
        JSONParser jsonParser = new JSONParser();

        try(BufferedReader readTask = new BufferedReader(new FileReader(NEW_FILE_PATH))){
            String line = readTask.readLine();
            while(line != null ){
                
                try{
                    JSONObject task = (JSONObject) jsonParser.parse(line);

                    if (id == ((Long) task.get("ID")).intValue()) {
                        task.put("Status", "in-progress");
                        task.put("Updated_At",calendar.getTime().toString());
                    }
                    tasks.add(task);
                     
                } catch(ParseException p) {
                    p.printStackTrace();
                }
                line = readTask.readLine();
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
        
        System.out.println("The task has been successfully marked as in-progress.");
    }


    //Method to mark the task as done
    @SuppressWarnings("unchecked")
    public void mark_done(int id){
        List<JSONObject> tasks = new ArrayList<>(); 
        JSONParser jsonParser = new JSONParser();

        try(BufferedReader readTask = new BufferedReader(new FileReader(NEW_FILE_PATH))){
            String line = readTask.readLine();
            while(line != null ){
                
                try{
                    JSONObject task = (JSONObject) jsonParser.parse(line);

                    if (id == ((Long) task.get("ID")).intValue()) {
                        task.put("Status", "done");
                        task.put("Updated_At",calendar.getTime().toString());
                    }
                    tasks.add(task);
                     
                } catch(ParseException p) {
                    p.printStackTrace();
                }
                line = readTask.readLine();
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
        
        System.out.println("The task has been successfully marked as done.");
    }


    //Method to list all the tasks
    public void list() {
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
        
    }


    //Method to list the tasks that are done
    public void list_done(){
        final String STAT1 = "done";
        JSONParser jsonParser = new JSONParser();
        
        try(BufferedReader readTask = new BufferedReader(new FileReader(NEW_FILE_PATH))){
            String line = readTask.readLine();
            System.out.println("List of tasks done: \n");
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

                    }
                    
                } catch(ParseException p) {
                    p.printStackTrace();
                }
                line = readTask.readLine();
            }
            
        } catch(IOException e){

            e.printStackTrace();
        }

    }


    //Method to list the tasks that are todo 
    public void list_todo() {

        final String STAT2 = "todo";
        JSONParser jsonParser = new JSONParser();
        
        try(BufferedReader readTask = new BufferedReader(new FileReader(NEW_FILE_PATH))){
            String line = readTask.readLine();
            System.out.println("List of tasks toto: \n");
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

                    }
                    
                } catch(ParseException p) {
                    p.printStackTrace();
                }
                line = readTask.readLine();
            }
            
        } catch(IOException e){

            e.printStackTrace();
        }    
    }


    //Method to list the tasks in progress
    public void list_in_progress(){
        final String STAT3 = "in-progress";
        JSONParser jsonParser = new JSONParser();
        
        try(BufferedReader readTask = new BufferedReader(new FileReader(NEW_FILE_PATH))){
            String line = readTask.readLine();
            System.out.println("List of tasks in progress: \n");
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

                    }
                    
                } catch(ParseException p) {
                    p.printStackTrace();
                }
                line = readTask.readLine();
            }
            
        } catch(IOException e){

            e.printStackTrace();
        }
    }



}
