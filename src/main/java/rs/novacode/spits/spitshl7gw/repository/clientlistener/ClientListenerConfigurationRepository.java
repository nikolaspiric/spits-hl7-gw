package rs.novacode.spits.spitshl7gw.repository.clientlistener;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.novacode.spits.spitshl7gw.model.ClientListenerConfiguration;

import java.util.Optional;

@Repository
public interface ClientListenerConfigurationRepository extends PagingAndSortingRepository<ClientListenerConfiguration, Long> {

    Page<ClientListenerConfiguration> findAll(Specification<ClientListenerConfiguration> specification, Pageable pageable);

    Optional<ClientListenerConfiguration> findById(Long id);

    Optional<ClientListenerConfiguration> findByClientName(String clientName);
}
