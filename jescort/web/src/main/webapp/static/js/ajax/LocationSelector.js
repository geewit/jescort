var locations = [];
var temp = [];

function updateLocations(element, selectedId)
{
    if(!locations || locations.length == 0)
    {
        $.getJSON('/api/locations/all', function(data) {
            locations = data;
        });
    }
    temp = locations;
    if(selectedId)
    {
        var len = temp.length;
        for(var i = 0; i < len; i++)
        {
            if(temp[i].id == selectedId)
            {
                temp = temp[i].children;
                break;
            }
        }
    }
    $.each(temp, function(i, item)
    {
        var option = new Option(item.name, item.id);
        $("#" + element).append(option);
    });
}


function updateLocations(element)
{
    if (!locations || locations.length == 0)
    {
        locations = jQuery.parseJSON("/api/locations/all");
    }
    $.each(locations, function(i, item)
    {
        var option = new Option(item.name, item.id);
        $("#" + element).append(option);
    });
}
