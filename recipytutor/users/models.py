from django.db import models
from django.contrib.auth.models import AbstractUser
from django.utils.text import slugify
import uuid

class MyUser(AbstractUser):
    profile_picture = models.ImageField(upload_to='profile_pics/', blank=True, null=True)
    # Additional field like 'UserId' that will be saved automatically
    UserId = models.CharField(max_length=150, unique=True, blank=True, null=True)

    def save(self, *args, **kwargs):
        # Automatically generate username if not provided
        if not self.username:
            base_username = slugify(self.first_name or self.email.split('@')[0])
            self.username = base_username

        # Automatically generate UserId if not provided (UUID example)
        if not self.UserId:
            self.UserId = str(uuid.uuid4())  # You can use slugify or another logic if needed

        super().save(*args, **kwargs)
