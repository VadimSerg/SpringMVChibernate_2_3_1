
package web.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { AppConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {

        return new Class[]{ WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    protected Filter[]  getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return  new Filter[] { encodingFilter };
    }

//    @Override
//    public  void onStartup(ServletContext aServletContext)  throws ServletException {
//        super.onStartup(aServletContext);
//        registerCharacterEncodingFilter(aServletContext);
//        registerHiddenFieldFilter(aServletContext);
//
//    }
//
//    private void registerHiddenFieldFilter(ServletContext aServletContext) {
//        aServletContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter()).
//                addMappingForUrlPatterns(null, true, "/*");
//    }
//
//    private void registerCharacterEncodingFilter(ServletContext aContext) {
//        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST,DispatcherType.FORWARD);
//
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        characterEncodingFilter.setForceEncoding(true);
//
//        FilterRegistration.Dynamic characterEncoding =
//                aContext.addFilter("characterEncoding",characterEncodingFilter);
//        characterEncoding.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
//    }
}