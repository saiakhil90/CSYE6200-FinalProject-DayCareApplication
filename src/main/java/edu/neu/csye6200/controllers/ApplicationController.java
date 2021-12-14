package edu.neu.csye6200.controllers;

import edu.neu.csye6200.Listeners;
import edu.neu.csye6200.Utils.Constants;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Stack;

/**
 * @author SaiAkhil
 */
@Service
@Scope("singleton")
public class ApplicationController implements ApplicationContextAware, Listeners.AppControlEventListener {

    @Value("${application.name}")
    private String appName;
    @Value("${application.appBackground}")
    protected String defaultAppBackground;
    @Value("${application.backgroundType}")
    protected int defaultBackgroundType;
    @Value("${application.preferredWidth}")
    private int preferredWidth;
    @Value("${application.preferredHeight}")
    private int preferredHeight;

    /**
     * Used to maintain UI Frames Stack
     * OnBackPressed currentStack will be removed
     * New Will be added onNextScreen
     */
    private Stack<AppViewsController> applicationStack;

    /**
     * Application context used to get respective beans onDemand
     */
    private ApplicationContext applicationContext;


    /**
     * Used to initialize stack and push first most ViewFrame
     * Method will be called post construct (On Created)
     */
    @PostConstruct
    public void onControllerCreated(){
        Constants.APP_PREFERRED_HEIGHT = this.preferredHeight;
        Constants.APP_PREFERRED_WIDTH = this.preferredWidth;
        Constants.APP_NAME = this.appName;
        applicationStack = new Stack<>();
        pushAndShowPage(applicationContext.getBean(LandingPageController.class));
    }

    private void pushAndShowPage(AppViewsController controller){
        if (!this.applicationStack.isEmpty()) return;
        this.applicationStack.push(controller);
        this.applicationStack.peek().onPagePushedToForeground(this);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public <T> void onGoToNextScreenEvent(Class<T> appViewsController) {
        this.pushAndShowPage((AppViewsController) this.applicationContext.getBean(appViewsController));
    }
}
