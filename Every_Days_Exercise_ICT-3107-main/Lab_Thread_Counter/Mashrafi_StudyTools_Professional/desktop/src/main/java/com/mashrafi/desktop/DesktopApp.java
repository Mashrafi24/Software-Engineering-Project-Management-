package com.mashrafi.desktop;
import com.mashrafi.core.*;
import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.List;

public class DesktopApp extends Application {
 private final StudyRepository repo=new StudyRepository();
 private final ObservableList<GanttTask> tasks=FXCollections.observableArrayList();
 private final ObservableList<Calculation> history=FXCollections.observableArrayList();
 private Label status=new Label("Connecting to MySQL...");
 private final String css="-fx-font-family: 'Arial'; -fx-background-color: #f3f6fb;";
 @Override public void start(Stage stage){
  BorderPane root=new BorderPane();root.setStyle(css);
  VBox header=new VBox(5);header.setPadding(new Insets(22,28,18,28));header.setStyle("-fx-background-color: linear-gradient(to right,#102a43,#245b83);");
  Label title=new Label("MASHRAFI STUDY TOOLS");title.setTextFill(Color.WHITE);title.setStyle("-fx-font-size:23px;-fx-font-weight:bold;");
  Label sub=new Label("Scientific Calculator  •  Project Planner  •  MySQL Workspace");sub.setTextFill(Color.web("#d9e9f5"));header.getChildren().addAll(title,sub);root.setTop(header);
  TabPane tabs=new TabPane();tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
  tabs.getTabs().addAll(new Tab("Scientific Calculator",calculatorTab()),new Tab("30-Day Gantt Planner",plannerTab()),new Tab("Calculation History",historyTab()));
  root.setCenter(tabs);HBox footer=new HBox(status);footer.setPadding(new Insets(12,22,12,22));footer.setStyle("-fx-background-color:white;-fx-border-color:#e1e8f0;");root.setBottom(footer);
  Scene scene=new Scene(root,1050,760);stage.setTitle("Mashrafi Study Tools — Professional Edition");stage.setMinWidth(900);stage.setMinHeight(650);stage.setScene(scene);stage.show();
  try{repo.initialize();refreshAll();status.setText("● MySQL connected successfully");status.setStyle("-fx-text-fill:#16804a;-fx-font-weight:bold;");}catch(Exception e){status.setText("MySQL error: "+e.getMessage());status.setStyle("-fx-text-fill:#b42318;");}
 }
 private VBox calculatorTab(){
  VBox box=new VBox(16);box.setPadding(new Insets(24));Label heading=new Label("Scientific Calculator");heading.setStyle("-fx-font-size:22px;-fx-font-weight:bold;-fx-text-fill:#17324d;");
  TextField display=new TextField();display.setPromptText("Enter first number");display.setStyle("-fx-font-size:20px;-fx-padding:14px;");TextField second=new TextField();second.setPromptText("Second number (not required for unary operations)");
  ComboBox<String> op=new ComboBox<>(FXCollections.observableArrayList("add","subtract","multiply","divide","sin","cos","tan","asin","acos","sqrt","cbrt","log","ln","square","power","factorial","percent"));op.setValue("add");op.setMaxWidth(Double.MAX_VALUE);
  CheckBox degrees=new CheckBox("Use degrees for trigonometric functions");degrees.setSelected(true);
  Label result=new Label("Result will appear here");result.setStyle("-fx-font-size:20px;-fx-font-weight:bold;-fx-text-fill:#176b48;");
  Button calculate=primary("Calculate & Save");calculate.setOnAction(e->{try{double x=Double.parseDouble(display.getText().trim()),y=second.getText().isBlank()?0:Double.parseDouble(second.getText().trim());double value=Calculator.calculate(x,y,op.getValue(),degrees.isSelected());repo.saveCalculation(display.getText()+" "+op.getValue()+" "+second.getText(),Double.toString(value));result.setText("= "+value);refreshHistory();status.setText("Calculation saved to MySQL.");}catch(Exception ex){result.setText("Error: "+ex.getMessage());}});
  GridPane keys=new GridPane();keys.setHgap(8);keys.setVgap(8);String[][] buttons={{"7","8","9","÷"},{"4","5","6","×"},{"1","2","3","−"},{"0",".","C","+"}};
  for(int r=0;r<buttons.length;r++)for(int c=0;c<buttons[r].length;c++){String v=buttons[r][c];Button b=new Button(v);b.setMaxWidth(Double.MAX_VALUE);b.setStyle("-fx-font-size:17px;-fx-padding:13px;-fx-background-color:white;-fx-border-color:#d8e1eb;-fx-background-radius:8;-fx-border-radius:8;");b.setOnAction(e->{if(v.equals("C"))display.clear();else if(v.equals("+")||v.equals("−")||v.equals("×")||v.equals("÷")){op.setValue(switch(v){case "+"->"add";case "−"->"subtract";case "×"->"multiply";default->"divide";});}else display.appendText(v);});keys.add(b,c,r);}
  for(int c=0;c<4;c++)keys.getColumnConstraints().add(new ColumnConstraints(120));
  VBox card=new VBox(12,heading,new Label("First value"),display,new Label("Second value"),second,new Label("Operation"),op,degrees,calculate,result,new Separator(),new Label("Quick input keypad"),keys);card.setPadding(new Insets(22));card.setMaxWidth(650);card.setStyle("-fx-background-color:white;-fx-background-radius:16;-fx-effect:dropshadow(gaussian,rgba(20,40,70,0.10),16,0,0,4);");box.setAlignment(Pos.TOP_CENTER);box.getChildren().add(card);return box;
 }
 private VBox plannerTab(){
  VBox box=new VBox(14);box.setPadding(new Insets(20));Label heading=new Label("30-Day Student Study Project Plan");heading.setStyle("-fx-font-size:21px;-fx-font-weight:bold;-fx-text-fill:#17324d;");
  HBox controls=new HBox(10);DatePicker start=new DatePicker(LocalDate.now());Button generate=primary("Generate 30 days");Button reload=new Button("Refresh");Button edit=primary("Update selected task");Button delete=new Button("Delete selected");
  controls.getChildren().addAll(new Label("Start date:"),start,generate,reload,edit,delete);
  TableView<GanttTask> table=new TableView<>(tasks);table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
  TableColumn<GanttTask,Number> day=new TableColumn<>("Day");day.setCellValueFactory(v->new javafx.beans.property.SimpleIntegerProperty(v.getValue().dayNumber()));
  TableColumn<GanttTask,LocalDate> date=new TableColumn<>("Date");date.setCellValueFactory(v->new javafx.beans.property.SimpleObjectProperty<>(v.getValue().taskDate()));
  TableColumn<GanttTask,String> name=new TableColumn<>("Task / Deliverable");name.setCellValueFactory(v->new javafx.beans.property.SimpleStringProperty(v.getValue().title()));
  TableColumn<GanttTask,Number> progress=new TableColumn<>("Progress %");progress.setCellValueFactory(v->new javafx.beans.property.SimpleIntegerProperty(v.getValue().progress()));
  TableColumn<GanttTask,String> state=new TableColumn<>("Status");state.setCellValueFactory(v->new javafx.beans.property.SimpleStringProperty(v.getValue().status()));
  table.getColumns().addAll(day,date,name,progress,state);table.setPrefHeight(360);
  generate.setOnAction(e->{try{repo.savePlan(GanttPlanner.create30DayPlan(start.getValue()));refreshTasks();status.setText("30-day plan saved in MySQL.");}catch(Exception ex){showError(ex);}});
  reload.setOnAction(e->refreshTasks());
  edit.setOnAction(e->{GanttTask t=table.getSelectionModel().getSelectedItem();if(t==null){status.setText("Select a task first.");return;}TextInputDialog d=new TextInputDialog(Integer.toString(t.progress()));d.setTitle("Update task progress");d.setHeaderText(t.title());d.setContentText("Progress (0–100):");d.showAndWait().ifPresent(v->{try{int p=Integer.parseInt(v);String s=p==100?"Completed":p==0?"Pending":"In Progress";repo.updateTask(t.id(),p,s);refreshTasks();status.setText("Task updated.");}catch(Exception ex){showError(ex);}});});
  delete.setOnAction(e->{GanttTask t=table.getSelectionModel().getSelectedItem();if(t==null)return;Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Delete "+t.title()+"?",ButtonType.YES,ButtonType.NO);a.showAndWait().ifPresent(b->{if(b==ButtonType.YES)try{repo.deleteTask(t.id());refreshTasks();}catch(Exception ex){showError(ex);}});});
  BarChart<String,Number> chart=makeChart();Button chartRefresh=new Button("Refresh chart");chartRefresh.setOnAction(e->populateChart(chart));
  VBox.setVgrow(table,Priority.ALWAYS);box.getChildren().addAll(heading,controls,table,new HBox(10,new Label("Progress overview"),chartRefresh),chart);populateChart(chart);return box;
 }
 private BarChart<String,Number> makeChart(){CategoryAxis x=new CategoryAxis();NumberAxis y=new NumberAxis(0,100,20);x.setLabel("Task");y.setLabel("Progress (%)");BarChart<String,Number> chart=new BarChart<>(x,y);chart.setTitle("Task Progress Overview");chart.setLegendVisible(false);chart.setPrefHeight(220);return chart;}
 private void populateChart(BarChart<String,Number> chart){chart.getData().clear();XYChart.Series<String,Number>s=new XYChart.Series<>();for(GanttTask t:tasks)s.getData().add(new XYChart.Data<>("D"+t.dayNumber(),t.progress()));chart.getData().add(s);}
 private VBox historyTab(){VBox box=new VBox(12);box.setPadding(new Insets(22));Label h=new Label("Recent Calculation History");h.setStyle("-fx-font-size:21px;-fx-font-weight:bold;");TableView<Calculation> table=new TableView<>(history);
  TableColumn<Calculation,Number> id=new TableColumn<>("ID");id.setCellValueFactory(v->new javafx.beans.property.SimpleLongProperty(v.getValue().id()));
  TableColumn<Calculation,String> exp=new TableColumn<>("Expression");exp.setCellValueFactory(v->new javafx.beans.property.SimpleStringProperty(v.getValue().expression()));
  TableColumn<Calculation,String> res=new TableColumn<>("Result");res.setCellValueFactory(v->new javafx.beans.property.SimpleStringProperty(v.getValue().result()));
  TableColumn<Calculation,String> time=new TableColumn<>("Created");time.setCellValueFactory(v->new javafx.beans.property.SimpleStringProperty(String.valueOf(v.getValue().createdAt())));
  table.getColumns().addAll(id,exp,res,time);Button refresh=primary("Refresh history");refresh.setOnAction(e->refreshHistory());box.getChildren().addAll(h,refresh,table);return box;
 }
 private Button primary(String text){Button b=new Button(text);b.setStyle("-fx-background-color:#245b83;-fx-text-fill:white;-fx-font-weight:bold;-fx-padding:10 16;-fx-background-radius:8;");return b;}
 private void refreshAll(){refreshTasks();refreshHistory();}
 private void refreshTasks(){try{tasks.setAll(repo.getTasks());}catch(Exception e){showError(e);}}
 private void refreshHistory(){try{history.setAll(repo.getRecentCalculations(100));}catch(Exception e){showError(e);}}
 private void showError(Exception e){status.setText("Error: "+e.getMessage());}
 public static void main(String[] args){launch(args);}
}