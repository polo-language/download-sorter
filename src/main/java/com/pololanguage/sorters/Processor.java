package com.pololanguage.sorters;

/**
 * The processor that determines how/when scanning and sorting occurs.
 */
public interface Processor extends Runnable {
  /** Returns a user-friendly String describing the processor type, functioning, etc. */
  public String getDescription();
}
