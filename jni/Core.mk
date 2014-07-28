LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

MY_ROOT := ../../../GitHub/smartPDF/
#MY_ROOT := ../..

V8 := v8-3.9

ifeq ($(TARGET_ARCH),arm)
LOCAL_CFLAGS += -DARCH_ARM -DARCH_THUMB -DARCH_ARM_CAN_LOAD_UNALIGNED
ifdef NDK_PROFILER
LOCAL_CFLAGS += -pg -DNDK_PROFILER
endif
endif
LOCAL_CFLAGS += -DAA_BITS=8
ifdef MEMENTO
LOCAL_CFLAGS += -DMEMENTO -DMEMENTO_LEAKONLY
endif

LOCAL_C_INCLUDES := \
	$(MY_ROOT)/thirdparty/jbig2dec \
	$(MY_ROOT)/thirdparty/openjpeg/src/lib/openjp2 \
	$(MY_ROOT)/thirdparty/jpeg \
	$(MY_ROOT)/thirdparty/zlib \
	$(MY_ROOT)/thirdparty/freetype/include \
	$(MY_ROOT)/source/fitz \
	$(MY_ROOT)/source/pdf \
	$(MY_ROOT)/source/xps \
	$(MY_ROOT)/source/cbz \
	$(MY_ROOT)/source/img \
	$(MY_ROOT)/scripts \
	$(MY_ROOT)/generated \
	$(MY_ROOT)/resources \
	$(MY_ROOT)/include \
	$(MY_ROOT)
ifdef V8_BUILD
LOCAL_C_INCLUDES += ../../thirdparty/$(V8)/include
endif

LOCAL_MODULE    := mypdfcore
LOCAL_SRC_FILES := \
	$(wildcard $(MY_ROOT)/source/fitz/*.c) \
	$(wildcard $(MY_ROOT)/source/pdf/*.c) \
	$(wildcard $(MY_ROOT)/source/xps/*.c) \
	$(wildcard $(MY_ROOT)/source/cbz/*.c) \
	$(wildcard $(MY_ROOT)/source/img/*.c)
ifdef MEMENTO
LOCAL_SRC_FILES += $(MY_ROOT)/fitz/memento.c
endif
ifdef V8_BUILD
LOCAL_SRC_FILES += \
	$(MY_ROOT)/source/pdf/js/pdf-js.c \
	$(MY_ROOT)/source/pdf/js/pdf-jsimp-cpp.c \
	$(MY_ROOT)/source/pdf/js/pdf-jsimp-v8.cpp
else
LOCAL_SRC_FILES += \
	$(MY_ROOT)/source/pdf/js/pdf-js-none.c
endif

LOCAL_LDLIBS    := -lm -llog -ljnigraphics

LOCAL_SRC_FILES := $(addprefix ../, $(LOCAL_SRC_FILES))

include $(BUILD_STATIC_LIBRARY)
