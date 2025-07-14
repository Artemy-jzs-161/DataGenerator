package org.cbr.oldFiles.interfaces;

import org.cbr.generationsData.GenerationConfig;

public interface DataGenerator <T>{
    T generate();
    T generate(GenerationConfig config);
}
