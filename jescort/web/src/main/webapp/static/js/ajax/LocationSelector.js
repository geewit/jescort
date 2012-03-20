var locations_level = 1;
var locations_temp = [];

function updateLocations(thiselement, element, selectedId)
{
    if(thiselement == "province")
    {
        locations_level = 1;
    }
    var firstOption = $("#" + element).find("option:first")[0];
    $("#" + element).find("option").remove().end().append(firstOption);
    if(element != "province" && $("#province").val() < 10)
    {
        return;
    }
    $.getJSON('/api/locations/all', function(data) {
        if(locations_level == 1)
        {
            locations_temp = data;
        }
        if(selectedId)
        {
            for(var i = 0; i < locations_temp.length; i++)
            {
                if(locations_temp[i].id == selectedId)
                {
                    locations_temp = locations_temp[i].children;
                    locations_level++;
                    break;
                }
            }
        }
        $.each(locations_temp, function(i, item)
        {
            var notappend = false;
            $("#" + element).children().each(function()
            {
                if($(this).val() == item.id)
                {
                    notappend = true;
                    return false;
                }
            });
            if(!notappend)
            {
                var option = new Option(item.name, item.id);
                $("#" + element).append(option);
            }
        });
    });
}

