package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import org.junit.jupiter.api.*;

@TestMethodOrder(OrderAnnotation.class)
class ServerTest extends AbstractServerTest {

  @Test
  @Order(1)
  void testCreate() {
    String response = sendMessage("VASYA CREATE_TASK CleanRoom");
    assertEquals("CREATED", response);
  }

  @Test
  @Order(2)
  void testCreateSameName() {
    String response = sendMessage("VASYA CREATE_TASK CleanRoom");
    assertEquals("ERROR", response);
  }

  @Test
  @Order(3)
  void testCreate2() {
    String response = sendMessage("VASYA CREATE_TASK task");
    assertEquals("CREATED", response);
  }

  @Test
  @Order(4)
  void testReopenFalse() {
    String response = sendMessage("VASYA REOPEN_TASK task");
    assertEquals("ERROR", response);
  }

  @Test
  @Order(5)
  void testClose() {
    String response = sendMessage("VASYA CLOSE_TASK task");
    assertEquals("CLOSED", response);
  }

  @Test
  @Order(6)
  void testCloseFalse() {
    String response = sendMessage("VASYA CLOSE_TASK task");
    assertEquals("ERROR", response);
  }

  @Test
  @Order(7)
  void testReopen() {
    String response = sendMessage("VASYA REOPEN_TASK task");
    assertEquals("REOPENED", response);
  }

  @Test
  @Order(8)
  void testDeleteNoAccess() {
    String response = sendMessage("PETYA DELETE_TASK CleanRoom");
    assertEquals("ACCESS_DENIED", response);
  }

  @Test
  @Order(9)
  void testList() {
    String response = sendMessage("PETYA LIST_TASK VASYA");
    assertEquals("TASKS [CleanRoom, task]", response);
  }

  @Test
  @Order(10)
  void testDeleteNotClosed() {
    String response = sendMessage("VASYA DELETE_TASK CleanRoom");
    assertEquals("ERROR", response);
  }

  @Test
  @Order(11)
  void testClose2() {
    String response = sendMessage("VASYA CLOSE_TASK CleanRoom");
    assertEquals("CLOSED", response);
  }

  @Test
  @Order(12)
  void testDelete() {
    String response = sendMessage("VASYA DELETE_TASK CleanRoom");
    assertEquals("DELETED", response);
  }

  @Test
  @Order(13)
  void testList2() {
    String response = sendMessage("VASYA LIST_TASK VASYA");
    assertEquals("TASKS [task]", response);
  }

  @Test
  @Order(0)
  void testArgs() {
    String response = sendMessage("VASYA CREATE_TASK");
    assertEquals("WRONG_FORMAT", response);
  }

}