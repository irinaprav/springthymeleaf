package com.alevel.thyme.thymetodo.todo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "todos")
public class Todo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false,name = "text")
    private  String text;

    @Column(nullable = false,name = "is_done")
    private  Boolean done;

    public Todo(){

    }

    public Todo(String text) {
        this.text = text;
        this.done = false;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Boolean isDone() {
        return done;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id) &&
                Objects.equals(text, todo.text) &&
                Objects.equals(done, todo.done);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, done);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", done=" + done +
                '}';
    }
}