package noob.practising.database;

import noob.practising.dao.DirectorDao;
import noob.practising.model.Director;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;


@RunWith(MockitoJUnitRunner.class)
public class DirectorDaoTest {

    @Mock
    private DirectorDao directorDao;


    @Test(expected = NullPointerException.class)
    public void testsSettingPropertiesFileWhenPropertiesIsNull() {
        when(directorDao.setProperties(null)).thenThrow(NullPointerException.class);
        directorDao.setProperties(null);
    }

    @Test
    public void testFindWithId() {
        when(directorDao.find(1)).thenReturn(new Director(1, "Juan", "España"));
        Assert.assertNotNull(directorDao.find(1));
        Assert.assertEquals(directorDao.find(1), new Director(1, "Roberto Juan", "España"));
    }

    @Test
    public void testFind() {
        Director d1 = new Director(1, "Pepa", "España");
        Director d2 = new Director(2, "Lin", "China");
        Director d3 = new Director(3, "Marta", "Italia");
        when(directorDao.find()).thenReturn(Arrays.asList(d1, d2, d3));

        Assert.assertNotNull(directorDao.find());
        Assert.assertEquals(directorDao.find().size(), 3);

        Assert.assertEquals(directorDao.find().get(0), new Director(1, "", ""));
        Assert.assertEquals(directorDao.find().get(1), new Director(2, "", ""));
        Assert.assertEquals(directorDao.find().get(2), new Director(3, "", ""));
    }

    @Test
    public void testAddDirector() {
        directorDao.add(new Director(1, "", ""));

        verify(directorDao, times(1))
                .add(new Director(1, "", ""));
    }

    @Test
    public void testUpdateDirector() {
        Director oldDirector = new Director(1, "Pedro Gomes", "Espana");
        // As dao.update is a void method we have to avoid when() directly
        // Why: (Java compiler does not like void methods between brackets)
        doAnswer(invocation -> {
            // Mocking the actual update
            System.out.println("Old director before update: " + oldDirector);
            Director directorToUpdate = invocation.getArgument(0);
            Assert.assertEquals(directorToUpdate.getId(), oldDirector.getId());
            oldDirector.setId(directorToUpdate.getId());
            oldDirector.setName(directorToUpdate.getName());
            oldDirector.setNationality(directorToUpdate.getNationality());
            System.out.println("Old director after update: " + oldDirector);
            return null;
        }).when(directorDao).update(any(Director.class));

        directorDao.update(new Director(1, "Pedro Gomez", "España"));

        Assert.assertEquals("Pedro Gomez", oldDirector.getName());
        Assert.assertEquals("España", oldDirector.getNationality());
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateDirector_WhenDirectorIsNull() {
        doThrow(new NullPointerException()).when(directorDao).update(nullable(Director.class));
        directorDao.update(null);
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteDirector_WhenDirectorIsNull() {
        doThrow(new NullPointerException()).when(directorDao).delete(nullable(Director.class));
        directorDao.delete(null);
    }

    @Test
    public void testDeleteDirector() {
        when(directorDao.find(anyInt())).thenReturn(new Director(1, "Juan", "España"));
        Assert.assertNotNull(directorDao.find(1));

        when(directorDao.find(1)).thenReturn(null);
        directorDao.delete(new Director(1, "Juan", "España"));
        Assert.assertNull(directorDao.find(1));
    }

}
