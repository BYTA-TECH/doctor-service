/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.bytatech.ayoos.doctor.avro;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Doctor extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -5968689716464221302L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Doctor\",\"namespace\":\"com.bytatech.ayoos.doctor.avro\",\"fields\":[{\"name\":\"name\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Doctor> ENCODER =
      new BinaryMessageEncoder<Doctor>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Doctor> DECODER =
      new BinaryMessageDecoder<Doctor>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Doctor> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Doctor> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Doctor>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Doctor to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Doctor from a ByteBuffer. */
  public static Doctor fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.String name;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Doctor() {}

  /**
   * All-args constructor.
   * @param name The new value for name
   */
  public Doctor(java.lang.String name) {
    this.name = name;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return name;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: name = (java.lang.String)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'name' field.
   * @return The value of the 'name' field.
   */
  public java.lang.String getName() {
    return name;
  }

  /**
   * Sets the value of the 'name' field.
   * @param value the value to set.
   */
  public void setName(java.lang.String value) {
    this.name = value;
  }

  /**
   * Creates a new Doctor RecordBuilder.
   * @return A new Doctor RecordBuilder
   */
  public static com.bytatech.ayoos.doctor.avro.Doctor.Builder newBuilder() {
    return new com.bytatech.ayoos.doctor.avro.Doctor.Builder();
  }

  /**
   * Creates a new Doctor RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Doctor RecordBuilder
   */
  public static com.bytatech.ayoos.doctor.avro.Doctor.Builder newBuilder(com.bytatech.ayoos.doctor.avro.Doctor.Builder other) {
    return new com.bytatech.ayoos.doctor.avro.Doctor.Builder(other);
  }

  /**
   * Creates a new Doctor RecordBuilder by copying an existing Doctor instance.
   * @param other The existing instance to copy.
   * @return A new Doctor RecordBuilder
   */
  public static com.bytatech.ayoos.doctor.avro.Doctor.Builder newBuilder(com.bytatech.ayoos.doctor.avro.Doctor other) {
    return new com.bytatech.ayoos.doctor.avro.Doctor.Builder(other);
  }

  /**
   * RecordBuilder for Doctor instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Doctor>
    implements org.apache.avro.data.RecordBuilder<Doctor> {

    private java.lang.String name;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.bytatech.ayoos.doctor.avro.Doctor.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Doctor instance
     * @param other The existing instance to copy.
     */
    private Builder(com.bytatech.ayoos.doctor.avro.Doctor other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
    }

    /**
      * Gets the value of the 'name' field.
      * @return The value.
      */
    public java.lang.String getName() {
      return name;
    }

    /**
      * Sets the value of the 'name' field.
      * @param value The value of 'name'.
      * @return This builder.
      */
    public com.bytatech.ayoos.doctor.avro.Doctor.Builder setName(java.lang.String value) {
      validate(fields()[0], value);
      this.name = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'name' field has been set.
      * @return True if the 'name' field has been set, false otherwise.
      */
    public boolean hasName() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'name' field.
      * @return This builder.
      */
    public com.bytatech.ayoos.doctor.avro.Doctor.Builder clearName() {
      name = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Doctor build() {
      try {
        Doctor record = new Doctor();
        record.name = fieldSetFlags()[0] ? this.name : (java.lang.String) defaultValue(fields()[0]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Doctor>
    WRITER$ = (org.apache.avro.io.DatumWriter<Doctor>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Doctor>
    READER$ = (org.apache.avro.io.DatumReader<Doctor>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
