from django.db import models
from django.contrib.auth.models import AbstractUser

class MyUser(AbstractUser):
    profile_picture = models.ImageField(upload_to='profile_pics/', blank=True, null=True)
    bio = models.TextField(blank=True, null=True)
