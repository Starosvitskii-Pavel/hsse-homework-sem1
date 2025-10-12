package com.mipt.pavelstarosvitskiy.generics;

public class Pair<K, V> {
  private K key;
  private V value;

  public Pair(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public K getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }

  public void setKey(K key) { this.key = key; }

  public void setValue(V value) {
    this.value = value;
  }

  public Pair<V, K> swap() {
    return new Pair<>(this.getValue(), this.getKey());
  }

  @Override
  public String toString() {
    return "Pair{key=" + this.getKey() + ", value=" + this.getValue() + "}";
  }
}


