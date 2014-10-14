package com.viii.battle.net.protocol; 
import ortobuf.core.CodedInputStream;
import ortobuf.core.CodedOutputStream;
import ortobuf.core.InvalidProtocolBufferException;
import ortobuf.core.ProtoEnum;
import ortobuf.core.AbstractMessageBuilder;

import java.io.IOException;

public final class Protocol {

	public static final class SPing {

		private Builder builder;
		public String greeting;

		private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

		public static SPing parse(byte[] data) throws IOException {
			return SPing.parse(new CodedInputStream(data));
		}

		public static SPing parse(CodedInputStream is) throws IOException {
			boolean done = false;
			int bitMask = 0x0000;
			SPing instance = new SPing();
			while (!done) {
				int tag = is.readTag();
				switch (tag) {
					default:
						throw InvalidProtocolBufferException.invalidTag();
					case 0:
						done = true;
						break;
					case 10:
						bitMask |= 0x1;
						instance.greeting = is.readString();
						break;
				}
			}
			if ((bitMask & 0x0) != 0x0) {
				throw new IOException("Message missing required fields");
			}
			return instance;
		}

		 /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
		        return result;
        }

		public Builder serialize() {
			return serialize(true);
		}

		public Builder serialize(boolean createBuilder) {
			if (builder != null) return builder;
			builder = createBuilder? Builder.create() : obtainBuilder();
			if (greeting != null) {
				builder.setGreeting(greeting);
			}
			return builder;
		}

		@Override
        public String toString() {
			return "SPing{" +
				"greeting = '" + greeting + '\'' +
				'}';
		}

		public static final class Builder extends AbstractMessageBuilder {

			private int bitMask;

			public static Builder create() {
                return new Builder();
            }

            public Builder() {
               super(50);
            }

			public Builder setGreeting (String greeting) {
				bitMask |= 1;
				os.writeInt32NoTag(10);
				os.writeString(greeting);
				return this;
			}
			public void initDefaults() {
			}
			}

		}

		public static final class CPing {

			private Builder builder;
			public String info;

			private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

			public static CPing parse(byte[] data) throws IOException {
				return CPing.parse(new CodedInputStream(data));
			}

			public static CPing parse(CodedInputStream is) throws IOException {
				boolean done = false;
				int bitMask = 0x0000;
				CPing instance = new CPing();
				while (!done) {
					int tag = is.readTag();
					switch (tag) {
						default:
							throw InvalidProtocolBufferException.invalidTag();
						case 0:
							done = true;
							break;
						case 10:
							bitMask |= 0x1;
							instance.info = is.readString();
							break;
					}
				}
				if ((bitMask & 0x0) != 0x0) {
					throw new IOException("Message missing required fields");
				}
				return instance;
			}

			 /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
			        return result;
        }

			public Builder serialize() {
				return serialize(true);
			}

			public Builder serialize(boolean createBuilder) {
				if (builder != null) return builder;
				builder = createBuilder? Builder.create() : obtainBuilder();
				if (info != null) {
					builder.setInfo(info);
				}
				return builder;
			}

			@Override
        public String toString() {
				return "CPing{" +
					"info = '" + info + '\'' +
					'}';
			}

			public static final class Builder extends AbstractMessageBuilder {

				private int bitMask;

				public static Builder create() {
                return new Builder();
            }

            public Builder() {
               super(50);
            }

				public Builder setInfo (String info) {
					bitMask |= 1;
					os.writeInt32NoTag(10);
					os.writeString(info);
					return this;
				}
				public void initDefaults() {
				}
				}

			}

}

