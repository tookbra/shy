package com.tookbra.shy.config;

import org.apache.velocity.context.Context;
import org.apache.velocity.tools.ToolboxFactory;
import org.apache.velocity.tools.config.ToolboxConfiguration;
import org.apache.velocity.tools.config.XmlFactoryConfiguration;
import org.apache.velocity.tools.view.ViewToolContext;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.view.velocity.VelocityLayoutView;
import org.springframework.web.servlet.view.velocity.VelocityToolboxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;

/**
 * Created by tookbra on 2016/7/12.
 */
public class VelocityLayoutToolboxView extends VelocityLayoutView {

    private static ToolboxFactory  toolboxFactory  = null;

    @Override
    protected Context createVelocityContext(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ViewToolContext ctx = new ViewToolContext(this.getVelocityEngine(), request, response, this.getServletContext());
        if (this.getToolboxConfigLocation() != null) {
            XmlFactoryConfiguration factory = new XmlFactoryConfiguration();
            factory.read(ResourceUtils.getURL(this.getToolboxConfigLocation()).openStream());
            ToolboxFactory toolboxFactory = factory.createFactory();
            toolboxFactory.configure(factory);
            Collection<ToolboxConfiguration> toolboxes = factory.getToolboxes();
            for (ToolboxConfiguration tc : toolboxes) {
                ctx.addToolbox(toolboxFactory.createToolbox(tc.getScope()));
            }


        }
        if (model != null && !model.isEmpty()) {
            ctx.putAll(model);
        }
        return ctx;
    }
}
