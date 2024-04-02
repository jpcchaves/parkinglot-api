package com.jpcchaves.parkinglotapi.domain.models;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "clients")
@EntityListeners(AuditingEntityListener.class)
public class Client extends AuditedEntity implements Serializable {
  @Serial
  private static final long serialVersionUID = -4727968164755556185L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(length = 100, nullable = false)
  private String name;

  @Column(nullable = false, unique = true, length = 11)
  private String cpf;

  @OneToOne(
      fetch = FetchType.LAZY
  )
  @JoinColumn(
      name = "user_id",
      nullable = false
  )
  private User user;


  public Client() {
  }

  public Client(LocalDateTime createdAt,
                LocalDateTime updatedAt,
                String createdBy,
                String modifiedBy,
                Long id,
                String name,
                String cpf,
                User user) {
    super(createdAt, updatedAt, createdBy, modifiedBy);
    this.id = id;
    this.name = name;
    this.cpf = cpf;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Client client = (Client) o;
    return Objects.equals(id, client.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Client{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", cpf='" + cpf + '\'' +
        ", user=" + user +
        '}';
  }
}
