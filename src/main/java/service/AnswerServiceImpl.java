package service;

import DAO.AnswerDao;
import DAO.AnswerDaoImpl;
import DAO.QuerieDao;
import DAO.QuerieDaoImpl;
import model.Answer;
import model.Querie;
import model.User;
import model.User_Test_Answer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Exception.DBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.DSInstance;

public class AnswerServiceImpl implements AnswerService {
    public AnswerServiceImpl() {
    }

    private final static Logger logger = LoggerFactory.getLogger(QuerieServiceImpl.class);

    private static final DataSource ds = DSInstance.getInstance().getDs();
    private final AnswerDao answerDao = new AnswerDaoImpl();

    @Override
    public Answer findById(int id) {
        return answerDao.findById(id);
    }

    @Override
    public boolean save(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        Answer answer = new Answer();
        answer.setAnswer(req.getParameter("answer"));
        answer.setCorrect(req.getParameter("correct") != null && req.getParameter("correct").equals("on"));
        answer.setQuerie_id(Integer.parseInt(req.getParameter("querie_Id")));

        return answerDao.save(answer);
    }

    @Override
    public boolean update(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        Answer answer = new Answer();

        answer.setId(Integer.parseInt(req.getParameter("answerId")));
        answer.setAnswer(req.getParameter("answer"));
        answer.setCorrect(req.getParameter("correct") != null && req.getParameter("correct").equals("on"));
        answer.setQuerie_id(Integer.parseInt(req.getParameter("querie_Id")));

        return answerDao.update(answer);
    }

    @Override
    public boolean delete(int id) throws DBException {
        return answerDao.delete(id);
    }

    @Override
    public boolean insertUser_Test_Answer(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws DBException {
        int user_Id = 0;
        int test_Id = 0;
        int answer_Id = 0;
        boolean correct = false;

        Connection con = null;
        try {
            con = ds.getConnection();
            con.setAutoCommit(false);
//        List<Querie> queries = (List<Querie>) servletContext.getAttribute("queries");
            List<Answer> answers = (List<Answer>) servletContext.getAttribute("answers");
           UserService userService = new UserServiceImpl();
            User user = userService.findByLogin((String) req.getSession().getAttribute("User"));
            for (int i = 0; i < answers.size(); i++) {
                User_Test_Answer user_test_answer = new User_Test_Answer();

                user_test_answer.setUser_test_user_id(user.getId());
                user_test_answer.setUser_test_test_id(Integer.parseInt(req.getParameter("test_Id")));
                user_test_answer.setAnswer_id(answers.get(i).getId());
                boolean userChecked = req.getParameter("userChecking" + i) != null && req.getParameter("userChecking" + i).equals("on");
                boolean answerCorrect = answers.get(i).isCorrect();

                user_test_answer.setCorrect(userChecked==answerCorrect);
                user_test_answer.setSelected(userChecked);

                answerDao.insertUser_Test_Answer(con, user_test_answer);
            }
            con.commit();
            servletContext.setAttribute("currentPage", Integer.parseInt( servletContext.getAttribute("currentPage").toString())+1);

        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DBException();
        }finally {
            if(con!=null){
                try {
                    con.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                    throw new DBException();
                }
            }
        }
        return true;
    }

    @Override
    public List<Answer> getAllAnswers(int id, int currentPage, int numOfRecords) throws DBException {
        return answerDao.getAllAnswers(id, currentPage, numOfRecords);
    }
    @Override
    public List<Answer> getAllAnswers_UserSubmition(int id, int currentPage, int numOfRecords) throws DBException {
        return answerDao.getAllAnswers_UserSubmition(id, currentPage, numOfRecords);
    }

    @Override
    public Integer getNumberOfRows() throws DBException {
        return answerDao.getNumberOfRows();
    }
}
