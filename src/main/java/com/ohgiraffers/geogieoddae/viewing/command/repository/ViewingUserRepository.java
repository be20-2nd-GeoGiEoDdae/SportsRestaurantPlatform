package com.ohgiraffers.geogieoddae.viewing.command.repository;

import com.ohgiraffers.geogieoddae.viewing.command.entity.ViewingUserEntity;
import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewingUserRepository extends JpaRepository<ViewingUserEntity,Long> {

}
