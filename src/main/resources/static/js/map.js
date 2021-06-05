let map, infoWindow;

function initMap() {
	
	  map = new google.maps.Map(document.getElementById("map"), {
    center: { lat: 50.630, lng: 3.057 },
    zoom: 14,
  });
  infoWindow = new google.maps.InfoWindow();
  const locationButton = document.createElement("button");
  locationButton.textContent = "Pan to Current Location";
  locationButton.classList.add("custom-map-control-button");
  const image = "/images/home.jpg";
  map.controls[google.maps.ControlPosition.TOP_CENTER].push(locationButton);
  locationButton.addEventListener("click", () => {
    // Try HTML5 geolocation.
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const pos = {
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          };
          marker = new google.maps.Marker({
        	    position: pos ,
        	    map,
        	    title: "locations",
        	    icon: image,
        	  });
          
          const contentString =
        	
        	  
        	  ' ';
          	const infowindow2 = new google.maps.InfoWindow({
                  content: contentString,
                  
              });
          	 marker.addListener("click", () => {
          		    infowindow2.open(map, marker);
          		  });
          infoWindow.open(map);
          map.setCenter(pos);
         
        },
        () => {
          handleLocationError(true, infoWindow, map.getCenter());
        }
      );
    } else {
      // Browser doesn't support Geolocation
      handleLocationError(false, infoWindow, map.getCenter());
    }

   
  });
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
  infoWindow.setPosition(pos);
  infoWindow.setContent(
    browserHasGeolocation
      ? "Error: The Geolocation service failed."
      : "Error: Your browser doesn't support geolocation."
  );
  infoWindow.open(map);
}
