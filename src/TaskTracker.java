//import java.io.FileWriter;
//import java.util.Scanner;
//import org.json.simple.*;
import java.io.IOException;
import org.json.simple.parser.ParseException;
/*import java.io.FileWriter;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.nio.file.Files;
import java.io.File;
import java.util.Arrays;
import java.nio.file.Path;
import java.nio.file.FileSystem;
import java.util.Date;
import java.util.Calendar;*/



public class TaskTracker {

    public static void commandList(){
        System.out.println("");
        System.out.println("Please, type the right command. These are the available commands: ");
        System.out.println("");
        System.out.println("    #1. Add a new task::    TasKTracker add \"Buy groceries\"\n");
        System.out.println("    #2. Update a task::     TasKTracker update 1 \"Buy groceries and cook dinner\" (where '1' is the id of the task you want to update)\n");
        System.out.println("    #3. Delete a task::     TasKTracker delete 1\n");
        System.out.println("    #4. Mark a task as in progress::    TasKTracker mark-in-progress 1\n");
        System.out.println("    #5. Mark a task as done::   TasKTracker mark-done 1\n");
        System.out.println("    #6. Listing all the tasks::     TasKTracker list\n");
        System.out.println("    #7. Listing tasks that are done::   TasKTracker list done \n");
        System.out.println("    #8. Listing tasks that are todo::   TasKTracker list todo \n");
        System.out.println("    #9. Listing tasks that are in progress::    TasKTracker list in-progress \n");

        System.out.println("");
    }
    public static void main(String[] args) throws IOException, ParseException{

        int nb_arguments = args.length;
        //System.out.println(nb_arguments);
        System.out.println("");
        System.out.println("##########################################################################################################################");
        System.out.println("                                             WELCOME TO YOUR CLI TASK TRACKER                                             ");
        System.out.println("##########################################################################################################################");
        System.out.println("");

        TaskManagement tasksManager = new TaskManagement();
        switch (nb_arguments) {
            case 1:
            //System.out.println(args[0]);
            //if (args[0].toLowerCase() == "list") {
            if ("list".equalsIgnoreCase(args[0])) {
                tasksManager.list();
            }
            else {
                commandList();
            }
                break;

            case 2:

                if ("add".equalsIgnoreCase(args[0])){
                    tasksManager.add(args[1]);
                }
                else if ("delete".equalsIgnoreCase(args[0])){
                    try{
                        int id = Integer.parseInt(args[1]);
                        tasksManager.delete(id);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                else if ("mark-in-progress".equalsIgnoreCase(args[0])){
                    try{
                        int id = Integer.parseInt(args[1]);
                        tasksManager.mark_in_progress(id);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                else if ("mark-done".equalsIgnoreCase(args[0])){
                    try{
                        int id = Integer.parseInt(args[1]);
                        tasksManager.mark_done(id);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                else if ("list".equalsIgnoreCase(args[0])){
                        if ("done".equalsIgnoreCase(args[1])){
                            tasksManager.list_done();
                        }
                        else if ("todo".equalsIgnoreCase(args[1])){
                            tasksManager.list_todo();
                        }
                        else if ("in-progress".equalsIgnoreCase(args[1])){
                            tasksManager.list_in_progress();
                        }
                        else{
                            commandList();
                            break;
                        }
                }
                else{ 
                    commandList();
                }

                 break;

            case 3:
                if ("update".equalsIgnoreCase(args[0])){
                    try{
                        int id = Integer.parseInt(args[1]);
                        tasksManager.update(id,args[2]);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                else {
                    commandList();
                }
                
                break;

            default:
                System.out.println("No command line arguments found! ");
                break;
        }



    }





    
}
