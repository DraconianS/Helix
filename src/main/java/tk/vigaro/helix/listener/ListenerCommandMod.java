package tk.vigaro.helix.listener;

//import com.sun.org.apache.bcel.internal.util.ClassLoader;
//import java.lang.ClassLoader;
//import org.pircbotx.hooks.Listener;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import tk.vigaro.helix.Commands;
import tk.vigaro.helix.Helix;

/**
 * Created by notauser on 5/14/16.
 *
 * */

public class ListenerCommandMod extends ListenerAdapter{
    Class aclass;
    @Override
    public void onMessage(MessageEvent event) throws Exception {
        if (event.getMessage().startsWith(Helix.botPrefix + Commands.mod)) {

            event.respond("thing 1");

            java.lang.ClassLoader classloader = new Helix().getClass().getClassLoader();


            try {
                event.respond("thing befire load");
                aclass = classloader.loadClass("mod.Test");

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                if (event.getBot().getConfiguration().getListenerManager().addListener(((Class<? extends ListenerAdapter>) aclass).newInstance())) {
                    event.respond("class loaded = " + aclass.getName());
                }
                else {
                    event.respond("Class not loaded");
                }
            }catch(Exception e){
                e.printStackTrace();
            }


        }
    }
}
