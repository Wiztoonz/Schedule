package ua.ubs.schedule.service.serviceImpl;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.ubs.schedule.entity.University;
import ua.ubs.schedule.repository.UniversityRepository;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UniversityServiceImplTest {

    @Mock
    private UniversityRepository universityRepository;

    @Test
    void addUniversityShouldSaveUniversity() {
        University university = mock(University.class);
        university.setId(1L);
        university.setUniversityName("UniversityName");
        university.setLocation("Location");
        university.setAddress("Address");

        when(universityRepository.save(university)).thenReturn(university);
        University savedUniversity = universityRepository.save(university);
        verify(universityRepository, times(1)).save(university);

        Assert.assertNotNull(savedUniversity);
    }

    @Test
    void updateUniversityByNameShouldUpdateUniversityByNameOfUniversity() {
        String universityName = "UniversityName";

        University university = mock(University.class);
        when(university.getId()).thenReturn(1L);
        when(university.getUniversityName()).thenReturn("UniversityName");
        when(university.getLocation()).thenReturn("Location");
        when(university.getAddress()).thenReturn("Address");

        University universityForUpdate = mock(University.class);
        when(universityForUpdate.getId()).thenReturn(1L);
        when(universityForUpdate.getUniversityName()).thenReturn("UniversityName_Updated");
        when(universityForUpdate.getLocation()).thenReturn("Location_Updated");
        when(universityForUpdate.getAddress()).thenReturn("Address_Updated");

        when(universityRepository.findUniversityByUniversityName(universityName)).thenReturn(university);

        University foundedUniversityByName = universityRepository.findUniversityByUniversityName(universityName);
        verify(universityRepository).findUniversityByUniversityName(universityName);
        Assert.assertNotNull(foundedUniversityByName);

        String universityNameForUpdate = universityForUpdate.getUniversityName();
        String locationForUpdate = universityForUpdate.getLocation();
        String addressForUpdate = universityForUpdate.getAddress();

        doCallRealMethod().when(foundedUniversityByName).setUniversityName(universityNameForUpdate);
        doCallRealMethod().when(foundedUniversityByName).setAddress(addressForUpdate);
        doCallRealMethod().when(foundedUniversityByName).setLocation(locationForUpdate);

        doCallRealMethod().when(foundedUniversityByName).getUniversityName();
        doCallRealMethod().when(foundedUniversityByName).getAddress();
        doCallRealMethod().when(foundedUniversityByName).getLocation();

        foundedUniversityByName.setUniversityName(universityNameForUpdate);
        foundedUniversityByName.setLocation(locationForUpdate);
        foundedUniversityByName.setAddress(addressForUpdate);

        Assert.assertEquals(foundedUniversityByName.getUniversityName(), universityNameForUpdate);
        Assert.assertEquals(foundedUniversityByName.getLocation(), locationForUpdate);
        Assert.assertEquals(foundedUniversityByName.getAddress(), addressForUpdate);
    }

}