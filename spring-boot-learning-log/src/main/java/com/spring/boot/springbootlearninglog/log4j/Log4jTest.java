package com.spring.boot.springbootlearninglog.log4j;

import org.apache.log4j.Logger;

/**
 * @author lianp
 * @date 2019/1/25 17:26
 * @since
 **/
public class Log4jTest {


    public static void main( String[] args ) {

        Logger logger = Logger.getLogger( Log4jTest.class.getName() );

        if ( logger.isInfoEnabled() ) {
            logger.info( "Hello World" );
        }
        if ( logger.isDebugEnabled() ) {
            logger.debug( "Hello World" );
        }
    }
}
