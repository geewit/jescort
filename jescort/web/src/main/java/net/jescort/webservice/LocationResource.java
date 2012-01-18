package net.jescort.webservice;

import java.util.List;
import javax.annotation.Resource;

import net.gelif.kernel.core.web.servlet.view.GsonView;
import net.jescort.domain.Location;
import net.jescort.persistence.dao.LocationDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LocationResource
{
    @RequestMapping(value = "/api/locations/all", method = RequestMethod.GET)
    public ModelAndView getAll()
    {
        List<Location> locations = locationDao.getByFirstLevel();
        ModelAndView mav = new ModelAndView(GsonView.instance);
        mav.addObject(GsonView.GSON_ROOT, locations);
        return mav;
    }

    @RequestMapping(value = "/api/locations/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Integer id)
    {
        Location location = locationDao.findOne(id);
        if(null == location)
        {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        ModelAndView mav = new ModelAndView(GsonView.instance);
        mav.addObject(GsonView.GSON_ROOT, location);
        return mav;
    }

    @RequestMapping(value = "/api/locations/{id}/children", method = RequestMethod.GET)
    public Object getChildren(@PathVariable("id") Integer id)
    {
        List<Location> locations = locationDao.getChildern(id);
        if(null == locations || locations.isEmpty())
        {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        ModelAndView mav = new ModelAndView(GsonView.instance);
        mav.addObject(GsonView.GSON_ROOT, locations);
        return mav;
    }

    @Resource(name = "locationDao")
    private LocationDao locationDao;
}
