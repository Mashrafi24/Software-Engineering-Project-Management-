package com.mashrafi.core;
import java.time.LocalDate;
import java.util.*;
public final class GanttPlanner {
 private static final String[] TITLES={"Project idea and objectives","Collect requirements","Feasibility analysis","Define project scope","Identify stakeholders","Functional requirements","Non-functional requirements","Select software process model","Prepare project schedule","Define milestones and deliverables","Use case diagram","Activity diagram","Data flow diagram","Entity relationship diagram","Class diagram","Object diagram","Review UML diagrams","Design database schema","Create database tables","Design application interface","Implement Java core classes","Implement study task management","Implement assignment tracking","Implement deadlines and status","Connect MySQL using JDBC","Unit testing","Integration testing","Fix defects and improve usability","Prepare documentation and screenshots","Final review and submission"};
 private GanttPlanner(){}
 public static List<GanttTask> create30DayPlan(LocalDate start){if(start==null)throw new IllegalArgumentException("Choose a start date");List<GanttTask> list=new ArrayList<>();for(int i=0;i<TITLES.length;i++)list.add(new GanttTask(0,i+1,start.plusDays(i),TITLES[i],"Day "+(i+1)+" deliverable: "+TITLES[i],0,"Pending"));return list;}
}