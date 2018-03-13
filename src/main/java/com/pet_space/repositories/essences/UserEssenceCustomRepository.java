package com.pet_space.repositories.essences;


public interface UserEssenceCustomRepository<T, ID> {
    void deleteById(ID id);

    void delete(T entity);

}
