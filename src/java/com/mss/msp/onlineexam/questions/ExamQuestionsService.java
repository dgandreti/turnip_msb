/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.onlineexam.questions;

import com.mss.msp.usr.task.TaskHandlerAction;
import com.mss.msp.util.ServiceLocatorException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author miracle
 */
public interface ExamQuestionsService {
   public String getCellContentValues(List list, ExamQuestionsAction examQuestionsAction, int count, String type, String columsString) throws ServiceLocatorException;
    public List doQuestionsSearch(ExamQuestionsAction examQuestionsAction) throws ServiceLocatorException;
     public ExamQuesVTO doExamQuestionsEdit(ExamQuestionsAction examQuestionsAction) throws ServiceLocatorException;
     public String addOrEditExamQuestionsEdit(ExamQuestionsAction examQuestionsAction) throws ServiceLocatorException;
      public List doQuestionsSearchList(ExamQuestionsAction examQuestionsAction) throws ServiceLocatorException;
           public String getImagePath(ExamQuestionsAction examQuestionsAction) throws ServiceLocatorException;

  
  
}
