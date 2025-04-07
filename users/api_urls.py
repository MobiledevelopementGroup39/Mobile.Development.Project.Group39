from django.urls import path
from .api_views import UserProfileUpdateView

urlpatterns = [
    path('update/', UserProfileUpdateView.as_view(), name='api_update_profile'),
]
