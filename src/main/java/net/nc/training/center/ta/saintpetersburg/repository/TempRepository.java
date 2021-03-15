package net.nc.training.center.ta.saintpetersburg.repository;

import net.nc.training.center.ta.saintpetersburg.model.TempModelMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempRepository extends JpaRepository<TempModelMessage, String> {
}
