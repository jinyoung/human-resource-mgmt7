
package human.resource.mgmt.query;


import human.resource.mgmt.event.*;
import human.resource.mgmt.aggregate.*;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.io.IOException;

@Service
@ProcessingGroup("vacationStatus")
public class VacationStatusCQRSHandlerReusingAggregate {

    @Autowired
    private VacationReadModelRepository repository;

    @Autowired
    private QueryUpdateEmitter queryUpdateEmitter;

    @QueryHandler
    public List<VacationReadModel> handle(VacationStatusQuery query) {
        return repository.findAll();
    }

    @QueryHandler
    public Optional<VacationReadModel> handle(VacationStatusSingleQuery query) {
        return repository.findById(query.getId());
    }

    @EventHandler
    public void whenVacationRegistered_then_CREATE (VacationRegisteredEvent event) throws Exception{
        VacationReadModel entity = new VacationReadModel();
        VacationAggregate aggregate = new VacationAggregate();
        aggregate.on(event);

        BeanUtils.copyProperties(aggregate, entity);
        
        repository.save(entity);

        queryUpdateEmitter.emit(VacationStatusQuery.class, query -> true, entity);

    }

    @EventHandler
    public void whenVacationCancelled_then_UPDATE (VacationCancelledEvent event) throws Exception{
        repository.findById(event.getId())
            .ifPresent(entity -> {

                VacationAggregate aggregate = new VacationAggregate();
       
                BeanUtils.copyProperties(entity, aggregate);
                aggregate.on(event);
                BeanUtils.copyProperties(aggregate, entity);

                repository.save(entity);

                queryUpdateEmitter.emit(VacationStatusSingleQuery.class, query -> query.getId().equals(event.getId(), entity);

            });

    }

    @EventHandler
    public void whenVacationApproved_then_UPDATE (VacationApprovedEvent event) throws Exception{
        repository.findById(event.getId())
            .ifPresent(entity -> {

                VacationAggregate aggregate = new VacationAggregate();
       
                BeanUtils.copyProperties(entity, aggregate);
                aggregate.on(event);
                BeanUtils.copyProperties(aggregate, entity);

                repository.save(entity);

                queryUpdateEmitter.emit(VacationStatusSingleQuery.class, query -> query.getId().equals(event.getId(), entity);

            });

    }

    @EventHandler
    public void whenVacationRejected_then_UPDATE (VacationRejectedEvent event) throws Exception{
        repository.findById(event.getId())
            .ifPresent(entity -> {

                VacationAggregate aggregate = new VacationAggregate();
       
                BeanUtils.copyProperties(entity, aggregate);
                aggregate.on(event);
                BeanUtils.copyProperties(aggregate, entity);

                repository.save(entity);

                queryUpdateEmitter.emit(VacationStatusSingleQuery.class, query -> query.getId().equals(event.getId(), entity);

            });

    }

    @EventHandler
    public void whenVacationUsed_then_UPDATE (VacationUsedEvent event) throws Exception{
        repository.findById(event.getId())
            .ifPresent(entity -> {

                VacationAggregate aggregate = new VacationAggregate();
       
                BeanUtils.copyProperties(entity, aggregate);
                aggregate.on(event);
                BeanUtils.copyProperties(aggregate, entity);

                repository.save(entity);

                queryUpdateEmitter.emit(VacationStatusSingleQuery.class, query -> query.getId().equals(event.getId(), entity);

            });

    }

}
