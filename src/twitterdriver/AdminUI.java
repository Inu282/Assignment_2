package twitterdriver;
import javafx.event.ActionEvent;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
//Singleton Pattern
public class AdminUI{
    
    private static AdminUI control;

    public static AdminUI getInstance(){
        if(control == null){
            control=new AdminUI();
                }
        
        return control;
    }
    private HBox menuBox;
    
    private AdminUI(){
        final Image rootPic= new Image("twitterdriver/Folder.png",30, 30, false, false);
        GroupContainer rootGroup=new GroupContainer("Root");
        
        Alert informationAlert=new Alert(Alert.AlertType.INFORMATION);
        
        TreeItem<Composite> root=new TreeItem<> (rootGroup, new ImageView(rootPic));
        root.setExpanded(true);
        TreeView<Composite> treeView=new TreeView<>(root);
        
        Button adduser = new Button();
        adduser.setText("Add User");
        TextField UserIDText = new TextField();
        UserIDText.setPromptText("Enter User ID");
        
        adduser.setOnAction((ActionEvent event) -> {
            TreeItem<Composite> selectedUser = treeView.getSelectionModel().getSelectedItem();
            String newUserInput= UserIDText.getText();
            if(rootGroup.containsUser(newUserInput)){
                informationAlert.setContentText("This user already belong to a group");
                informationAlert.showAndWait();
            }
            else{
                UserLeaf temp = new UserLeaf(newUserInput);
                ((GroupContainer) selectedUser.getValue()).addGroupUsers(temp);
                selectedUser.getChildren().add(new TreeItem<>(temp));
            }
            UserIDText.clear();
        });

        Button addgroup = new Button();
        addgroup.setText("Add Group");
        TextField GroupIDText = new TextField();
        GroupIDText.setPromptText("Enter Group ID");

        addgroup.setOnAction((ActionEvent event) -> {
            String newGroup= GroupIDText.getText();
            GroupContainer temp=new GroupContainer(newGroup);
            TreeItem<Composite> selectedGroup = treeView.getSelectionModel().getSelectedItem();

            if(rootGroup.containsGroup(newGroup)){
                informationAlert.setContentText("Group already exists");
                informationAlert.showAndWait();
            }
            else{
                temp.setCreationTime();
                ((GroupContainer) selectedGroup.getValue()).addGroupUsers(temp);
                selectedGroup.getChildren().add(new TreeItem<>(temp, new ImageView(rootPic)));
            }
            GroupIDText.clear();
        });
        Button LastUpdatedUser=new Button("Last Updated User's ID");
        LastUpdatedUser.setOnAction((ActionEvent event) -> {
            LastUpdatedIDVisitor updatedIDVisitor=new LastUpdatedIDVisitor();
            rootGroup.accept(updatedIDVisitor);
            informationAlert.setContentText("User's last updated: " 
                    + updatedIDVisitor.getLastUpdateUser()); 
            informationAlert.showAndWait();
        });

        Button userView = new Button();
        userView.setText("Open User View");
        
        userView.setOnAction((ActionEvent event) -> {
            TreeItem<Composite> selectedUser = treeView.getSelectionModel().getSelectedItem();
            if (selectedUser.getValue() instanceof UserLeaf){
                UserLeaf userViewUser = (UserLeaf) selectedUser.getValue();
                UserUI.openUserUI(userViewUser, rootGroup);
            }
        });

        Button usertotal = new Button();
        usertotal.setText("Show Total # of Users");
        
        usertotal.setOnAction((ActionEvent event) -> {
            TotalUser userTotalVisitor=new TotalUser();
            rootGroup.accept(userTotalVisitor);
            informationAlert.setContentText("There are " + userTotalVisitor.getUserTotal()
                    +" users total");
            informationAlert.showAndWait();
        });

        Button grouptotal = new Button();
        grouptotal.setText("Show Total # of Groups"); 
        
        grouptotal.setOnAction((ActionEvent event) -> {
            TotalGroup groupTotalVisitor=new TotalGroup();
            rootGroup.accept(groupTotalVisitor);
            informationAlert.setContentText("There are " + groupTotalVisitor.getGroupTotal()
                    + " groups total");
            informationAlert.showAndWait();
            
        });

        Button messagetotal = new Button();
        messagetotal.setText("Show Total Messages");
        
        messagetotal.setOnAction((ActionEvent event) -> {
            TotalMessage messageTotalVisitor=new TotalMessage();
            rootGroup.accept(messageTotalVisitor);
            informationAlert.setContentText("There are " + messageTotalVisitor.getMessageTotal() + " total tweets");
            informationAlert.showAndWait();
        });

        Button positive = new Button();
        positive.setText("Show Positive %");

        positive.setOnAction((ActionEvent event)-> {
            PositiveMsgs positiveVisitor = new PositiveMsgs();
            rootGroup.accept(positiveVisitor);
            informationAlert.setContentText(String.format("There are %.2f percent"
                    + " of positive messages total" , positiveVisitor.getPositivePercentage()));
            
            informationAlert.showAndWait();
        });

        VBox treeBox=new VBox(treeView);
        HBox userBox = new HBox(10, UserIDText, adduser);
        HBox groupBox = new HBox(10, GroupIDText, addgroup);
        HBox UserGroupBox = new HBox(10, usertotal, grouptotal);
        HBox MessagePositiveBox = new HBox(10, messagetotal, positive);
        
        VBox UserButtons=new VBox(10, LastUpdatedUser, userView);
        UserButtons.setAlignment(Pos.CENTER);
        
        VBox topButtons = new VBox(10, userBox, groupBox, UserButtons, UserGroupBox, 
                MessagePositiveBox);
        VBox bottomButtons=new VBox(10, UserGroupBox, MessagePositiveBox);
        VBox allButtons=new VBox(10, topButtons, bottomButtons);
        
        allButtons.setSpacing(170);
        menuBox = new HBox(10, treeBox, allButtons);
        menuBox.setPadding(new Insets(10));

    }
    public HBox getAdminPanel() {
            return menuBox;
    }
}
