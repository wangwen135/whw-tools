 /**
 * Copyright(C) 2015-2025 杏仁科技
 * All rights reserved
 * 2015年8月22日 Created
 */
package com.xingren.test;

import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author wwh
 * @date 2015年8月22日 下午5:41:06 
 *
 */
public class VelocitStaticTemplate {
    public static void main(String[] args) {
        
        VelocityEngine engine = new VelocityEngine();  
        engine.init();  
        VelocityContext ctx = new VelocityContext();  
        ctx.put("aaa", "this is a \n newline test");  
        ctx.put("bbb", "\n");  
        ctx.put("ccc", "<br/>");  
        String template = "这是一个静态模板测试${aaa} ${bbb} ${ccc}";  
        
        StringWriter writer = new StringWriter();  
        engine.evaluate(ctx, writer, "", template);  
        System.out.println(writer.toString());  
    }  

}
