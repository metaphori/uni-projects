package asw1022.services;

import asw1022.util.xml.ManageXML;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.w3c.dom.Document;

/**
 * It represents the asynchronous context of the user.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
public class UserAsyncContext extends AsyncAdapter {

    final String _name;
    final AtomicReference<AsyncContext> _async = new AtomicReference<AsyncContext>();
    final Queue<Document> _queue = new LinkedList<Document>();
    Logger logger;

    UserAsyncContext(String name) {
        _name = name;
        logger = Logger.getLogger("Logger Timeout " + name);
    }

    @Override
    public void onTimeout(AsyncEvent aev) {
        try {
            ManageXML mngXML = new ManageXML();
            Document answer = mngXML.newDocument("timeout");

            synchronized(this){
                AsyncContext async = _async.get();
                if (async != null && _async.compareAndSet(async, null)) {
                    logger.info("Timeout fired. Closing and nulling async context for " + this._name);
                    OutputStream tos = async.getResponse().getOutputStream();
                    mngXML.transform(tos, answer);
                    tos.close();
                    async.complete();
                } else{
                    logger.info("Timeout fired for " + this._name + " but AsyncCtx is null");
                }
            }
        } catch (Exception ex) {
            System.out.println("Eccezione in timeout: " + ex);
        }
    }

}
