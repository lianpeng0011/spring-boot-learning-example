package com.spring.boot.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link ServerEndpoint}
 *
 * @author lianp
 * @date 2019/1/11 16:24
 * @since
 **/
@Component
@ServerEndpoint( "/chat/{user-name}" )
public class ChatRoomServerEndpoint {

    private static Map<String, Session> livingSessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen( @PathParam( "user-name" ) String userName, Session session ) {
        livingSessions.put( session.getId(), session );
        StringBuilder message = new StringBuilder();
        message.append( "欢迎" ).append( userName ).append( "上线 " );
        sendAll( message.toString() );
    }

    @OnMessage
    public void onMassage( @PathParam( "user-name" ) String userName, String message ) {
        StringBuilder text = new StringBuilder();
        text.append( "[" ).append( userName ).append( "]" ).append( message );
        sendAll( text.toString() );
    }

    @OnError
    public void onException( Session session ,Throwable throwable) {
        if ( !session.isOpen() ) {
            livingSessions.remove( session.getId() );
        }
    }

    @OnClose
    public void onClose( Session session ) {
        livingSessions.remove( session.getId() );
    }

    private void sendAll( String message ) {
        livingSessions.forEach( ( id, session ) -> {
            RemoteEndpoint.Basic basic = session.getBasicRemote();
            try {
                basic.sendText( message );
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        } );
    }
}
