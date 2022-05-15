package com.kwg.springframework.Context;/**
 * @Auther: kwg2001
 * @Date: 2022/5/5 15:48
 * @Description:
 */

import com.kwg.springframework.beans.factory.ListableBeanFactory;

/**
 * @program: my-spring
 *
 * @description: 应用上下文
 *
 * @author: Kwg
 *
 * @create: 2022-05-05 15:48
 *
 *  Central interface to provide configuration for an application.
 *  This is read-only while the application is running, but may be
 *  reloaded if the implementation supports this.
 *  为应用程序提供配置的中央接口。
 *  这在应用程序运行时是只读的，但如果实现支持，则可以重新加载。
 **/
public interface ApplicationContext extends ListableBeanFactory {
}
