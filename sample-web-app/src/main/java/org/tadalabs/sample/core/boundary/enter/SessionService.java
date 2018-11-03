package org.tadalabs.sample.core.boundary.enter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tadalabs.sample.core.domain.Session;
import org.tadalabs.sample.data.dynamo.entity.SessionEntity;
import org.tadalabs.sample.web.SessionListMapper;
import org.tadalabs.sample.web.SessionMapper;
import org.tadalabs.sample.web.api.SessionList;
import org.tadalabs.sample.data.dynamo.repository.SessionDynamoRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    private SessionDynamoRepository sessionDynamoRepository;

    @Autowired
    private SessionMapper sessionMapper;

    @Autowired
    private SessionListMapper sessionListMapper;

    public SessionService(SessionDynamoRepository sessionDynamoRepository) {
        this.sessionDynamoRepository = sessionDynamoRepository;
    }

    public SessionList findAll() {
        List<SessionEntity> entities =
                (List<SessionEntity>) sessionDynamoRepository.findAll();

        return sessionListMapper.toSessionList(entities);
    }

    /**
     * Inserts a new `Session`
     *
     * @param session model to be persisted in the database
     * @return {Optional} the newly instantiated `Session` object
     */
    public Optional<Session> addSession(Session session) {




        // map the session model to an entity, and attempt to save
        Optional<SessionEntity> optional = sessionMapper.fromDomain(session);

        if(!optional.isPresent()) {
            throw new InvalidParameterException("External session model failed to be mapped to an internal entity.");
        }

        SessionEntity entity = optional.get();

        entity = sessionDynamoRepository.save(entity);

        return sessionMapper.toDomain(entity);
    }

    /**
     * Retrieve the Session record corresponding to the param Id
     *
     * @param sessionId the Id of the Session object to fetch
     * @return {Optional} the persisted Session object
     */
    public Optional<Session> getSessionById(@Valid @NotBlank String sessionId) {

        Optional<SessionEntity> optional = this.sessionDynamoRepository.findBySessionId(sessionId);

        if (!optional.isPresent()) {
            throw new InvalidParameterException("Failed to find a Session matching param SessionId.");
        }

        return sessionMapper.toDomain(optional.get());
    }

    /**
     * Check whether the Session is still Valid or Not
     *
     * @param sessionId the unique Identifier associated with the Session
     * @param remoteAddress the sender's remote Address
     *
     * @return {boolean} True if the Session is valid, false if not
     */
    public boolean isSessionValid(@Valid @NotBlank String sessionId, @Valid @NotBlank String remoteAddress) {
        return sessionDynamoRepository.findBySessionIdAndAddress(sessionId, remoteAddress).isPresent();
    }

    /**
     * Expires the Session
     *
     * @param sessionId the unique Identifier corresponding to the session to Expire
     */
    public void expireSession(@Valid @NotBlank String sessionId) {
        sessionDynamoRepository.deleteById(sessionId);
    }

}

