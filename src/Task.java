import java.util.Calendar;
import java.util.Date;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.File;
import java.nio.file.Paths;
//import java.text.ParseException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Task {

    private int id;
    private String description;
    private String status ;
    private final Date createdAt;
    private Date updatedAt;
    private Calendar myCalendar = Calendar.getInstance();
    private static int idCount = 1;
    private static final String FILE_PATH = "tasks.json";

    public Task(String desc){

        this.id = idCount++;
        this.createdAt = myCalendar.getTime();
        this.updatedAt = myCalendar.getTime();
        this.description = desc;
        this.status = "todo";
        registerTask();
    }

    @SuppressWarnings("unchecked")
    private void registerTask(){

            String lastLine;
            try {
                FileWriter taskFile = new FileWriter(FILE_PATH,true);

                try{
                    List <String> lines  = Files.readAllLines(Paths.get(FILE_PATH));
                    
                    if (!lines.isEmpty()){
                        lastLine = lines.get(lines.size() - 1);
                        JSONParser myParser = new JSONParser();
                        try{ 
                            JSONObject theLastLine = (JSONObject) myParser.parse(lastLine);
                            Long id = (Long) theLastLine.get("ID");
        
                            JSONObject taskAttributes = new JSONObject();
                            taskAttributes.put("ID",id + 1);
                            taskAttributes.put("Description",this.description);
                            taskAttributes.put("Status",this.status);
                            taskAttributes.put("Created_At",this.createdAt.toString());
                            taskAttributes.put("Updated_At",this.updatedAt.toString());
                            taskFile.write(taskAttributes.toJSONString()+"\n");
                            taskFile.close();

                        } catch(ParseException e){
                            e.printStackTrace();
                        }

                    } else {

                        JSONObject taskAttributes = new JSONObject();
                        taskAttributes.put("ID",this.id);
                        taskAttributes.put("Description",this.description);
                        taskAttributes.put("Status",this.status);
                        taskAttributes.put("Created_At",this.createdAt.toString());
                        taskAttributes.put("Updated_At",this.updatedAt.toString());
                        taskFile.write(taskAttributes.toJSONString()+"\n");
                        taskFile.close();
                        
                    }  
                } catch(IOException e){
                    e.printStackTrace();
                }
            
            } catch(Exception e){
                e.printStackTrace();
            }
        

        //JSONObject entireTask= new JSONObject();
       /* String lastLine;
        try{
            List <String> lines  = Files.readAllLines(Paths.get(FILE_PATH));
            if (!lines.isEmpty()){
                lastLine = lines.get(lines.size() - 1);
                JSONParser myParser = new JSONParser();
                try{ 
                    JSONObject theLastLine = (JSONObject) myParser.parse(lastLine);
                    Long id = (Long) theLastLine.get("ID");

                    JSONObject taskAttributes = new JSONObject();
                    taskAttributes.put("ID",id + 1);
                    taskAttributes.put("Description",this.description);
                    taskAttributes.put("Status",this.status);
                    taskAttributes.put("Created_At",this.createdAt.toString());
                    taskAttributes.put("Updated_At",this.updatedAt.toString());

                    try {
                        FileWriter taskFile = new FileWriter(FILE_PATH,true);
                        taskFile.write(taskAttributes.toJSONString()+"\n");
                        taskFile.close();
                    } catch(IOException e){
                        e.printStackTrace();
                    } 
    
                } catch(ParseException e){
                    e.printStackTrace();
                }
            


            }
            else {

                JSONObject taskAttributes = new JSONObject();
                taskAttributes.put("ID",this.id);
                taskAttributes.put("Description",this.description);
                taskAttributes.put("Status",this.status);
                taskAttributes.put("Created_At",this.createdAt.toString());
                taskAttributes.put("Updated_At",this.updatedAt.toString());

                try {
                    FileWriter taskFile = new FileWriter(FILE_PATH,true);
                    taskFile.write(taskAttributes.toJSONString()+"\n");
                    taskFile.close();
                } catch(IOException e){
                    e.printStackTrace();
                } 

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }*/
        
    


    }

    public void setDescription(String newDescription){
        this.description = newDescription;
    }

    public void setStatus(String newStatus){
        this.status = newStatus;
    }

    public int getId(){
        return this.id;
    }

    public String getDescription(){
        return this.description;
    }

    public String getStatus(){
        return this.status;
    }

    public Date getCreatedAt(){
        return this.createdAt;
    }

    public Date getUpdatedAt(){
        return this.updatedAt;
    }

    public void add_task(){


    }

    
}
